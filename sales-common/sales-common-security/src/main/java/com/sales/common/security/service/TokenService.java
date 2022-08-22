package com.sales.common.security.service;

import com.sales.common.core.constant.CacheConstants;
import com.sales.common.core.enums.TokenStyleEnum;
import com.sales.common.core.utils.ServletUtils;
import com.sales.common.core.utils.StringUtils;
import com.sales.common.core.utils.TokenUtils;
import com.sales.common.core.utils.ip.IpUtils;
import com.sales.common.redis.service.RedisService;
import com.sales.common.security.auth.AuthTokenInfo;
import com.sales.common.security.auth.AuthUserType;
import com.sales.common.security.utils.SecurityUtils;
import com.sales.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author sales
 */
@Component
public class TokenService {
    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;


    /**
     * 创建令牌
     */
    public AuthTokenInfo createToken(LoginUser loginUser) {
        String accessToken = "";
        if (StringUtils.isEmpty(loginUser.getToken())) {
            accessToken = TokenUtils.createToken(TokenStyleEnum.RANDOM_32.getTypeName());
        } else {
            accessToken = loginUser.getToken();
        }
        loginUser.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        loginUser.setToken(accessToken);
        refreshToken(loginUser);
        AuthTokenInfo tokenInfo = new AuthTokenInfo();
        tokenInfo.setAccessToken(accessToken);
        tokenInfo.setExpiresIn(getExpireTime(loginUser.getUserType()));
        if(loginUser.getUserType().equals(AuthUserType.MOBILE.getType())){
            tokenInfo.setAccid(loginUser.getAccid());
            tokenInfo.setCompletedBaseInfo(loginUser.isCompletedBaseInfo());
        }
        return tokenInfo;
    }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser() {
        return getLoginUser(ServletUtils.getRequest());
    }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = SecurityUtils.getToken(request);
        return getLoginUser(token);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token) {
        LoginUser user = null;
        try {
            if (StringUtils.isNotEmpty(token)) {
                user = redisService.getCacheObject(getTokenKey(token));
                return user;
            }
        } catch (Exception e) {
        }
        return user;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            redisService.deleteObject(getTokenKey(token));
        }
    }

    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + getExpireTime(loginUser.getUserType()) * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, getExpireTime(loginUser.getUserType()), TimeUnit.MINUTES);
    }

    /**
     * @param token:
     * @return java.lang.String
     * @desc: 令牌缓存KEY
     * @author wuyingqiang
     * @date 2022/7/11 16:09
     */
    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * @param authUserType:
     * @return long
     * @desc: 获取有效时间
     * @author wuyingqiang
     * @date 2022/7/13 17:14
     */
    private long getExpireTime(String authUserType) {
        if (authUserType.equals(AuthUserType.MANAGE.getType())) {
            //一天
            return CacheConstants.EXPIRATION * 24;
        }
        if (authUserType.equals(AuthUserType.MOBILE.getType())) {
            //15天，半个月
            return CacheConstants.EXPIRATION * 24 * 15;
        }
        //默认为一小时
        return CacheConstants.EXPIRATION;
    }

}
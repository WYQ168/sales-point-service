package com.sales.common.security.utils;

import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.constant.TokenConstants;
import com.sales.common.core.context.SecurityContextHolder;
import com.sales.common.core.exception.base.BaseException;
import com.sales.common.core.utils.ServletUtils;
import com.sales.common.core.utils.StringUtils;
import com.sales.common.redis.service.RedisService;
import com.sales.system.api.domain.AppUser;
import com.sales.system.api.domain.SysUser;
import com.sales.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 权限获取工具类
 *
 * @author sales
 */
@Component
public class SecurityUtils {

    @Autowired
    private RedisService redisService;
    private static SecurityUtils securityUtils;

    @PostConstruct
    public void init() {
        securityUtils = this;
        securityUtils.redisService = this.redisService;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return SecurityContextHolder.getUserId();
    }

    /**
     * 获取用户名称
     */
    public static String getUsername() {
        return SecurityContextHolder.getUserName();
    }

    /**
     * 获取用户key
     */
    public static String getUserKey() {
        return SecurityContextHolder.getUserKey();
    }

    /**
     * 获取用户key
     */
    public static String getUserType() {
        return SecurityContextHolder.getUserKey();
    }

    /**
     * 获取登录用户信息
     */
    public static LoginUser getLoginUser() {
        return SecurityContextHolder.get(SecurityConstants.LOGIN_USER, LoginUser.class);
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        System.out.println("============" + ServletUtils.getRequest());
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        // 从header获取token标识
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static SysUser getSysUserData() {
        String token = getToken();
        LoginUser loginUser = securityUtils.redisService.getCacheObject("login_tokens:" + token);
        if (Objects.isNull(loginUser)) {
            throw new BaseException("请先登录");
        }
        SysUser sysUser = null;
        try {
            sysUser = loginUser.getSysUser();
        } catch (Exception e) {
            throw new BaseException("请先登录");
        }
        return sysUser;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static AppUser getUserData() {
        String token = getToken();
        LoginUser loginUser = securityUtils.redisService.getCacheObject("login_tokens:" + token);
        if (Objects.isNull(loginUser)) {
            throw new BaseException("请先登录");
        }
        AppUser user = null;
        try {
            user = loginUser.getUser();
        } catch (Exception e) {
            throw new BaseException("请先登录");
        }
        return user;
    }

    /**
     * 裁剪token前缀
     */
    public static String replaceTokenPrefix(String token) {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] arg) {
        System.out.println(encryptPassword("123456"));
        System.out.println(matchesPassword("123456", "$2a$10$SWpY5lDTIRrYZnPnJtYj0eydZCpt3DFiHyGWpcxCEmF8CeTJ2WWly"));
    }

}

package com.sales.auth.controller;

import com.sales.auth.enums.ErrorCodeEnum;
import com.sales.auth.enums.LoginType;
import com.sales.auth.form.LoginBody;
import com.sales.auth.form.RegisterBody;
import com.sales.auth.service.AppLoginService;
import com.sales.auth.service.SysLoginService;
import com.sales.common.core.constant.Constants;
import com.sales.common.core.domain.BaseResult;
import com.sales.common.core.exception.ServiceException;
import com.sales.common.core.utils.StringUtils;
import com.sales.common.redis.service.RedisService;
import com.sales.common.security.auth.AuthTokenInfo;
import com.sales.common.security.auth.AuthUserType;
import com.sales.common.security.auth.AuthUtil;
import com.sales.common.security.service.TokenService;
import com.sales.common.security.utils.SecurityUtils;
import com.sales.system.api.domain.request.PassWordModifyReq;
import com.sales.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Random;

/**
 * 用户授权模块
 *
 * @author sales
 */
@RestController
@Slf4j
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private AppLoginService appLoginService;
    @Autowired
    private RedisService redisService;

    private Random rand;

    /**
     * 用户登陆
     *
     * @param loginBody:
     * @return com.sales.common.core.domain.R<com.sales.common.security.auth.AuthTokenInfo>
     * @author wuyingqiang
     * @date 2022/7/14 16:08
     */
    @PostMapping("login")
    public BaseResult<AuthTokenInfo> login(@RequestBody @Valid LoginBody loginBody) {
        if (!LoginType.isInclude(loginBody.getLoginType())) {
            return BaseResult.fail(ErrorCodeEnum.ERR_LOGIN_TYPE);
        }
        LoginUser userInfo = new LoginUser();
        if (loginBody.getUserType().equals(AuthUserType.MANAGE.getType())) {
            // 管理端用户登录
            userInfo = sysLoginService.login(loginBody);
        } else if (loginBody.getUserType().equals(AuthUserType.WSPRO.getType())) {
            // app用户登录
            userInfo = appLoginService.login(loginBody);
        } else {
            return BaseResult.fail("登录类型错误！");
        }
        // 获取登录token
        return BaseResult.ok(tokenService.createToken(userInfo));
    }

    /**
     * 用户退出登陆
     */
    @PostMapping("logout")
    public BaseResult<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        LoginUser loginUser = tokenService.getLoginUser(token);
        if (StringUtils.isNotEmpty(token)) {
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            if (loginUser != null && loginUser.getUserType().equals(AuthUserType.MANAGE.getType())) {
                // 记录用户退出日志
                sysLoginService.logout(loginUser.getUsername());
            }
        }
        return BaseResult.ok();
    }

    /**
     * 令牌刷新
     */
    @PostMapping("refresh")
    public BaseResult<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return BaseResult.ok();
        }
        return BaseResult.ok();
    }

    /**
     * 用户注册
     *
     * @param registerBody
     * @return
     */
/*
    @PostMapping("register")
    public BaseResult<?> register(@RequestBody @Valid RegisterBody registerBody) {
        verifyUserPhone(registerBody.getPhone());
        checkSmsCode(registerBody);
        if (registerBody.getUserType().equals(AuthUserType.MANAGE.getType())) {
            // 管理端用户注册
            sysLoginService.register(registerBody);
        }
        if (registerBody.getUserType().equals(AuthUserType.WSPRO.getType())) {
            // app用户注册
            appLoginService.register(registerBody);
        }
        return BaseResult.ok();
    }
*/

    /**
     * 判断改手机号是否存在
     *
     * @return
     */
    @GetMapping("verifyUserPhone")
    public BaseResult<?> verifyUserPhone(@RequestParam("phone") String phone) {
        /**
         * 判断该手机号是否注册系统用户
         */
        Boolean aBoolean = sysLoginService.checkPhone(phone);
        /**
         * 判断该手机号是否注册普通用户
         */
        Boolean aBoolean1 = appLoginService.checkPhone(phone);
        /**
         * 只要该手机号被注册，就返回已被注册
         */
        if (aBoolean || aBoolean1) {
            throw new ServiceException("该手机号已被注册");
        }
        return BaseResult.ok();
    }

    /**
     * 短信验证码校验
     *
     * @param registerBody
     */
    public void checkSmsCode(RegisterBody registerBody) {
        String code = redisService.getCacheObject(Constants.SMS_CODE_KEY + registerBody.getUuid());
        if (code != null) {
            if (!code.isEmpty() && !code.equals(registerBody.getSmsCode())) {
                throw new ServiceException("验证码错误");
            }
            redisService.deleteObject(Constants.SMS_CODE_KEY + registerBody.getUuid());
        } else {
            throw new ServiceException("验证码已过期");
        }
    }
}

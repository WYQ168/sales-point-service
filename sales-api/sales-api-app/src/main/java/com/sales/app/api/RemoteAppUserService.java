package com.sales.app.api;

import com.sales.app.api.domain.AppUser;
import com.sales.common.core.constant.ServiceNameConstants;
import com.sales.app.api.factory.RemoteAppUserFallbackFactory;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.domain.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 * 
 * @author sales
 */
@FeignClient(contextId = "remoteAppUserService", value = ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteAppUserFallbackFactory.class)
public interface RemoteAppUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param userName 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/getUserInfo")
    public BaseResult<AppUser> getAppUserInfo(@RequestParam("userName") String userName, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param appUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/registerUser")
    public BaseResult<AppUser> registerUser(@RequestParam("userName") AppUser appUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 校验手机号是否注册
     * @param phone
     * @param inner
     * @return
     */
    @GetMapping("/user/checkPhone")
    BaseResult<Boolean> checkAppUserPhone(@RequestParam("phone")String phone, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);

}

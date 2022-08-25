package com.sales.system.api;

import com.alibaba.fastjson.JSONObject;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.constant.ServiceNameConstants;
import com.sales.common.core.domain.BaseResult;
import com.sales.system.api.domain.SysUser;
import com.sales.system.api.factory.RemoteSysUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务
 *
 * @author sales
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysUserFallbackFactory.class)
public interface RemoteSysUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/sysUser/info/{username}")
    public BaseResult<JSONObject> getSysUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/sysUser/register")
    public BaseResult<Boolean> registerSysUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 校验手机号是否注册
     * @param phone
     * @param inner
     * @return
     */
    @GetMapping("/sysUser/phone")
	BaseResult<Boolean> checkSysUserPhone(@RequestParam("phone") String phone, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);

	/**
	 * 修改系统用户密码
	 * @param user
	 * @param inner
	 * @return
	 */
	@PutMapping("/sysUser/password")
	BaseResult<Boolean> modifySysUserPassWord(@RequestBody SysUser user, @RequestHeader(SecurityConstants.FROM_SOURCE) String inner);
}

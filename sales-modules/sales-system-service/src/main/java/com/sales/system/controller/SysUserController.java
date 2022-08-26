package com.sales.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.entity.SysUser;
import com.sales.system.service.MenuManagerService;
import com.sales.system.service.SysUserService;
import com.sales.system.utils.UserDataUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统用户Api", value = "UserController")
@RestController
    @RequestMapping(value = "/sysUser")
@AllArgsConstructor
public class SysUserController {

    private SysUserService sysUserService;
    @Autowired
    private MenuManagerService menuService;

    @GetMapping("/info/{username}")
    public BaseResult<JSONObject> getSysUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source) {
        JSONObject loginUser = sysUserService.getSysUserInfo(username);
        return BaseResult.ok(loginUser);
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public BaseResult<?> getInfo() {
        SysUser sysUser = UserDataUtils.getUserData();
        sysUser.setSysPassword("");
        return BaseResult.ok(sysUser);
    }


    @RequestMapping(value = "/getRouterData", method = RequestMethod.GET)
    public BaseResult<?> getRouterData() {
        return BaseResult.ok(menuService.getRouterData());
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/getSysUserList", method = RequestMethod.GET)
    public BaseResult<?> getSysUserList() {
        return BaseResult.ok(null);
    }


}

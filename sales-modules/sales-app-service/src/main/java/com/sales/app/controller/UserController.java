package com.sales.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.sales.app.domain.entity.AppUser;
import com.sales.app.service.UserService;
import com.sales.common.core.constant.SecurityConstants;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户控制层
 * @author: wuyingqiang
 * @date: 2022-08-05 16:10
 */

@Api(value = "APP用户相关接口")
@RestController
@RequestMapping(value = "/baseUser")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据userId获取用户信息")
    @GetMapping("/getUserInfoById")
    public BaseResult<AppUser> getUserInfoById(Long userId){
        return BaseResult.ok(userService.getUserInfoById(userId));
    }

    @ApiModelProperty(value = "注册用户")
    @PostMapping("/registerUser")
    public BaseResult<Boolean> registerAppUserInfo(@RequestBody AppUser appUser){
        return BaseResult.ok(userService.register(appUser));
    }

    @ApiModelProperty(value = "通过用户名查询用户信息")
    @PostMapping("/getUserInfo")
    public BaseResult<JSONObject> getUserInfo(@RequestParam("userName") String userName, @RequestHeader(SecurityConstants.FROM_SOURCE) String source){
        return BaseResult.ok(userService.getUserInfo(userName));
    }

    @ApiModelProperty(value = "注销用户")
    @PostMapping("/cancelUserPermission")
    public BaseResult<Integer> cancelUserPermission(Long userId){
        return BaseResult.ok(userService.cancelUserPermission(userId));
    }
}

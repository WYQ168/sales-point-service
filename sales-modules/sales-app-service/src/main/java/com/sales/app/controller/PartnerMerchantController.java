package com.sales.app.controller;

import com.sales.app.domain.entity.AppUser;
import com.sales.app.domain.request.UserInfoReq;
import com.sales.app.service.UserService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 伙伴、商户控制层
 * @author: wuyingqiang
 * @date: 2022-08-05 16:30
 */

@Api(value = "我的伙伴、商户接口")
@RestController
@RequestMapping(value = "/partnerMerchant")
public class PartnerMerchantController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "我的伙伴列表")
    @GetMapping("/getPartnerList")
    public BaseResult<List<AppUser>> getPartnerList(UserInfoReq req){
        return BaseResult.ok(userService.getPartnersByCondition(req));
    }

    @ApiOperation(value = "我的商户列表")
    @GetMapping("/getMerchantList")
    public BaseResult<List<AppUser>> getMerchantList(UserInfoReq req){
        return BaseResult.ok(userService.getMerchantByCondition(req));
    }

}

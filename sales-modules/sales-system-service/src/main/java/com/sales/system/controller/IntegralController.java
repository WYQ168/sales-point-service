package com.sales.system.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.request.IntegralOrderReq;
import com.sales.system.service.IntegralManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 积分相关控制层
 * @author: wuyingqiang
 * @date: 2022-08-24 17:41
 */

@RestController
@RequestMapping("/integral")
public class IntegralController {
    @Autowired
    private IntegralManagerService integralManagerService;

    @ApiOperation(value = "后台审核兑换的积分订单")
    @PostMapping("/auditIntegralExchangeOrder")
    public BaseResult<Integer> auditIntegralExchangeOrder(@RequestBody IntegralOrderReq req){
        return BaseResult.ok(integralManagerService.auditIntegralExchangeOrder(req));
    }
}

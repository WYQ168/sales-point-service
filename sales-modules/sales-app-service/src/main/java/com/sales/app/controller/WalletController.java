package com.sales.app.controller;

import com.sales.app.domain.response.MyWalletResp;
import com.sales.app.service.WalletService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 钱包控制层
 * @author: wuyingqiang
 * @date: 2022-08-06 9:09
 */

@Api(value = "钱包相关接口")
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @ApiOperation(value = "获取我的钱包信息")
    @GetMapping("/getMyWalletInfo")
    public BaseResult<MyWalletResp> getMyWalletInfo(Long userId){
        return BaseResult.ok(walletService.getMyWalletInfo(userId));
    }

}

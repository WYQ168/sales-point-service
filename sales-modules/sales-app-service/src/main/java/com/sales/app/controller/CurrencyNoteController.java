package com.sales.app.controller;

import com.sales.app.domain.entity.CurrencyNote;
import com.sales.app.domain.request.CashPosBuyReq;
import com.sales.app.domain.request.CurrencyBuyReq;
import com.sales.app.domain.response.CurrencyNoteResp;
import com.sales.app.service.CurrencyNoteService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 流通券账户控制层
 * @author: wuyingqiang
 * @date: 2022-08-08 10:24
 */

@Api(value = "流通账户接口")
@RestController
@RequestMapping("/currency")
public class CurrencyNoteController {

    @Autowired
    private CurrencyNoteService currencyNoteService;

    @ApiOperation(value = "获取流通券账户信息")
    @GetMapping("/getCurrencyNoteInfo")
    public BaseResult<CurrencyNoteResp> getCurrencyNoteInfo(Long userId){
        return BaseResult.ok(currencyNoteService.getCurrencyInfo(userId));
    }

    @ApiOperation(value = "全款购买流通券")
    @PostMapping("/buyByFull")
    public BaseResult<Integer> buyByFull(@RequestBody CurrencyBuyReq req){
        return BaseResult.ok(currencyNoteService.buyByFull(req));
    }

    @ApiOperation(value = "现金+积分购买流通券")
    @PostMapping("/cashIntegralBuy")
    public BaseResult<Integer> cashIntegralBuy(@RequestBody CurrencyBuyReq req){
        return BaseResult.ok(currencyNoteService.cashIntegralBuy(req));
    }

    @ApiOperation(value = "现金+mpos购买流通券")
    @PostMapping("/cashAndPosBuy")
    public BaseResult<Integer> cashAndPosBuy(@RequestBody CashPosBuyReq req){
        return BaseResult.ok(currencyNoteService.cashAndPosBuy(req));
    }

}

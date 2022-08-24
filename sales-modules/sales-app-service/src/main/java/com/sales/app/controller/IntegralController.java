package com.sales.app.controller;

import com.sales.app.domain.entity.IncomeRecord;
import com.sales.app.domain.entity.SalesProduct;
import com.sales.app.domain.request.ExchangeIntegralReq;
import com.sales.app.domain.request.IntegralReq;
import com.sales.app.domain.response.IntegralResp;
import com.sales.app.service.IncomeRecordService;
import com.sales.app.service.SalesProductService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 积分兑换控制层
 * @author: wuyingqiang
 * @date: 2022-08-09 22:03
 */
@Api(value = "积分相关接口")
@RestController
@RequestMapping("/integral")
public class IntegralController {
    @Autowired
    private SalesProductService salesProductService;

    @Autowired
    private IncomeRecordService incomeRecordService;

    @ApiOperation(value = "积分兑换的产品列表")
    @GetMapping("/getIntegralProducts")
    public BaseResult<List<SalesProduct>> getIntegralProducts(@RequestParam("productLabel") String productLabel){
        return BaseResult.ok(salesProductService.getIntegralProducts(productLabel));
    }

    @ApiOperation(value = "发起积分兑换申请")
    @PostMapping("/exchangeSalesProduct")
    public BaseResult<Integer> exchangeSalesProduct(@RequestBody ExchangeIntegralReq req){
        return BaseResult.ok(salesProductService.exchangeSalesProduct(req));
    }

    @ApiOperation(value = "我的积分账户")
    @PostMapping("/getIntegralInfo")
    public BaseResult<List<IntegralResp>> getIntegralInfo(@RequestBody IntegralReq req){
        return BaseResult.ok(incomeRecordService.getIntegralInfo(req));
    }

    @ApiOperation(value = "我的积分账户的账单记录")
    @PostMapping("/getIncomeRecordListByIntegral")
    public BaseResult<List<IncomeRecord>> getIncomeRecordListByIntegral(@RequestBody IntegralReq req){
        return BaseResult.ok(incomeRecordService.getIncomeRecordListByIntegral(req));
    }


}

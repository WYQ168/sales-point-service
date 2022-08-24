package com.sales.app.controller;

import com.alipay.api.AlipayApiException;
import com.sales.app.domain.entity.SalesProduct;
import com.sales.app.domain.request.MachineByMallReq;
import com.sales.app.domain.request.MallProductReq;
import com.sales.app.domain.request.TradeInfoReq;
import com.sales.app.service.OrderService;
import com.sales.app.service.SalesProductService;
import com.sales.app.service.UnionpayService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description: 商城控制层
 * @author: wuyingqiang
 * @date: 2022-08-20 15:29
 */

@Api(value = "商城相关接口")
@RestController
@RequestMapping("/mall")
public class MallController {

    @Autowired
    private SalesProductService salesProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UnionpayService unionpayService;

    @ApiOperation(value = "商城POS机产品列表")
    @GetMapping("/getSalesProductList")
    public BaseResult<List<SalesProduct>> getSalesProductList(MallProductReq req){
        return BaseResult.ok(salesProductService.getProductList(req));
    }

    @ApiOperation(value = "商城POS机产品信息")
    @GetMapping("/getSalesProductInfo")
    public BaseResult<SalesProduct> getSalesProductInfo(@RequestParam("productId") Long productId){
        return BaseResult.ok(salesProductService.getProductInfo(productId));
    }

    @ApiOperation(value = "商城点击-购买POS机")
    @PostMapping("/buyMachineByMall")
    public BaseResult<String> buyMachineByMall(MachineByMallReq req) throws AlipayApiException {
        return BaseResult.ok(salesProductService.buyMachineByMall(req));
    }

    @ApiOperation(value = "银联侧调起支付接口")
    @PostMapping("/openAndConsume")
    public BaseResult<?> openAndConsume(HttpServletRequest req, HttpServletResponse resp, TradeInfoReq tradeInfo) throws IOException {
        unionpayService.openAndConsume(req, resp, tradeInfo);
        return BaseResult.ok();
    }

}

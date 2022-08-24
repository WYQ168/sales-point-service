package com.sales.app.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.sales.app.domain.entity.Order;
import com.sales.app.domain.request.IntegralOrderReq;
import com.sales.app.domain.request.OrderQueryReq;
import com.sales.app.domain.response.IntegralOrderResp;
import com.sales.app.service.OrderService;
import com.sales.app.service.SalesProductService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 订单相关接口
 * @author: wuyingqiang
 * @date: 2022-08-15 9:48
 */
@Api(value = "订单相关接口")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private SalesProductService salesProductService;

    @ApiOperation(value = "我的兑换订单列表")
    @GetMapping("/getIntegralOrderList")
    public BaseResult<List<IntegralOrderResp>> getIntegralOrderList(IntegralOrderReq req){
        return BaseResult.ok(orderService.getIntegralOrderList(req));
    }

    @ApiOperation(value = "待收货的礼包机具订单列表")
    @PostMapping("/getOrderListByGift")
    public BaseResult<List<Order>> getOrderListByGift(Long userId){
        return BaseResult.ok(salesProductService.getOrderListByGift(userId));
    }

    @ApiOperation(value = "待付款的礼包机具订单列表")
    @PostMapping("/getUnpaidGiftOrderList")
    public BaseResult<List<Order>> getUnpaidGiftOrderList(Long userId){
        return BaseResult.ok(salesProductService.getUnpaidGiftOrderList(userId));
    }

    @ApiOperation(value = "取消订单支付")
    @PostMapping("/cancelIntegralOrder")
    public BaseResult<Integer> cancelIntegralOrder(String orderId){
        return BaseResult.ok(orderService.cancelIntegralOrder(orderId));
    }

    @ApiOperation(value = "发起支付宝支付")
    @PostMapping("/goAliPay")
    public BaseResult<AlipayTradeAppPayResponse> goAliPay(String orderId) throws AlipayApiException {
        return BaseResult.ok(orderService.goAliPay(orderId));
    }

    @ApiOperation(value = "商城的订单列表")
    @GetMapping("/getMallOrderList")
    public BaseResult<List<Order>> getMallOrderList(OrderQueryReq req){
        return BaseResult.ok(orderService.getMallOrderList(req));
    }

}

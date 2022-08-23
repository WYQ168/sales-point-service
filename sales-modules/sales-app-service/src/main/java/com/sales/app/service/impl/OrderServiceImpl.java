package com.sales.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.sales.app.constants.AliPayConstants;
import com.sales.app.domain.entity.Order;
import com.sales.app.domain.request.IntegralOrderReq;
import com.sales.app.domain.response.IntegralOrderResp;
import com.sales.app.enums.OrderStatusEnum;
import com.sales.app.mapper.OrderMapper;
import com.sales.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 订单业务层
 * @author: wuyingqiang
 * @date: 2022-08-15 9:49
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AliPayConstants aliPayConstants;

    @Override
    public List<IntegralOrderResp> getIntegralOrderList(IntegralOrderReq req) {
        return orderMapper.selectIntegralOrderList(req);
    }

    @Override
    public Integer cancelIntegralOrder(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order != null){
            order.setOrderStatus(OrderStatusEnum.CANCEL_ORDER.getState());
            return orderMapper.updateByPrimaryKeySelective(order);
        }
        return 0;
    }

    @Override
    public AlipayTradeAppPayResponse goAliPay(String orderId) throws AlipayApiException {
        Order order = orderMapper.selectByPrimaryKey(orderId);

        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConstants.getServerUrl(), aliPayConstants.getAppId(),
                aliPayConstants.getPrivateKey(), "json", "GBK", aliPayConstants.getPublicKey(),
                aliPayConstants.getAlgorithm());
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(aliPayConstants.getNotifyUrl() + "?orderId=" + orderId);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", order.getOrderNo());
        bizContent.put("total_amount", order.getTotalPrice());
        bizContent.put("subject", JSONObject.parse(order.getGoodInfo()));
        bizContent.put("product_code", "QUICK_MSECURITY_PAY");

        request.setBizContent(bizContent.toString());
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        return response;
    }

}

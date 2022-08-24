package com.sales.app.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.sales.app.domain.entity.Order;
import com.sales.app.domain.request.IntegralOrderReq;
import com.sales.app.domain.request.OrderQueryReq;
import com.sales.app.domain.request.TradeInfoReq;
import com.sales.app.domain.response.IntegralOrderResp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface OrderService {

    /**
     * 获取我的兑换订单列表
     *
     * @param req 请求体
     * @return 兑换订单列表
     */
    List<IntegralOrderResp> getIntegralOrderList(IntegralOrderReq req);

    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @return
     */
    Integer cancelIntegralOrder(String orderId);

    /**
     * 发起订单的支付，获取网站支付的链接
     *
     * @param orderId 订单id
     * @return 支付结果
     */
    AlipayTradeAppPayResponse goAliPay(String orderId) throws AlipayApiException;

    /**
     * 获取商城的订单的列表
     *
     * @param req 订单请求类
     * @return 商城订单列表
     */
    List<Order> getMallOrderList(OrderQueryReq req);

}

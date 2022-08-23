package com.sales.app.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.sales.app.domain.request.IntegralOrderReq;
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
     * @param orderId 订单id
     * @return
     */
    AlipayTradeAppPayResponse goAliPay(String orderId) throws AlipayApiException;

}

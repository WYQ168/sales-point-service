package com.sales.system.service;

import com.sales.system.domain.request.IntegralOrderReq;

public interface IntegralManagerService {

    /**
     * 审核积分兑换的订单
     *
     * @param req 积分兑换订单请求体
     * @return 结果
     */
    Integer auditIntegralExchangeOrder(IntegralOrderReq req);
}

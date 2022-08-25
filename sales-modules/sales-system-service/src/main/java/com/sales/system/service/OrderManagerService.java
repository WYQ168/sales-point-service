package com.sales.system.service;

import com.sales.system.domain.entity.Order;
import com.sales.system.domain.request.OrderQueryReq;
import com.sales.system.domain.request.OrderUpdateReq;

import java.util.List;

public interface OrderManagerService {

    /**
     * 查询订单列表
     *
     * @param req 订单管理查询类
     * @return 订单管理列表
     */
    List<Order> getAllOrderList(OrderQueryReq req);

    /**
     * 后台更新订单状态：发货
     *
     * @param req 订单管理更新请求类
     * @return 结果
     */
    Integer updateOrderStatus(OrderUpdateReq req);
}

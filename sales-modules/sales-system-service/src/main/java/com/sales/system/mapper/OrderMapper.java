package com.sales.system.mapper;

import com.sales.system.domain.entity.Order;
import com.sales.system.domain.request.OrderQueryReq;
import com.sales.system.domain.request.OrderUpdateReq;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 查询订单管理的列表
     *
     * @param req 订单管理查询实体
     * @return 订单列表
     */
    List<Order> selectAllOrderList(OrderQueryReq req);

}
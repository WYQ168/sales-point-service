package com.sales.system.service.impl;

import com.sales.system.domain.entity.Order;
import com.sales.system.domain.request.OrderQueryReq;
import com.sales.system.domain.request.OrderUpdateReq;
import com.sales.system.mapper.OrderMapper;
import com.sales.system.service.OrderManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 订单管理业务层
 * @author: wuyingqiang
 * @date: 2022-08-25 11:17
 */

@Service
public class OrderManagerServiceImpl implements OrderManagerService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrderList(OrderQueryReq req) {
        return orderMapper.selectAllOrderList(req);
    }

    @Override
    public Integer updateOrderStatus(OrderUpdateReq req) {
        Order order = orderMapper.selectByPrimaryKey(req.getOrderId());
        if(order != null){
            BeanUtils.copyProperties(req,order);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}

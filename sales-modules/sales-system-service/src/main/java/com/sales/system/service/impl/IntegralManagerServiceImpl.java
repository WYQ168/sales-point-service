package com.sales.system.service.impl;

import com.sales.system.domain.entity.Order;
import com.sales.system.domain.request.IntegralOrderReq;
import com.sales.system.mapper.OrderMapper;
import com.sales.system.service.IntegralManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: 积分相关业务层
 * @author: wuyingqiang
 * @date: 2022-08-24 17:43
 */

@Service
public class IntegralManagerServiceImpl implements IntegralManagerService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer auditIntegralExchangeOrder(IntegralOrderReq req) {
        Order order = orderMapper.selectByPrimaryKey(req.getOrderId());
        order.setOrderStatus(req.getOrderStatus());
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}

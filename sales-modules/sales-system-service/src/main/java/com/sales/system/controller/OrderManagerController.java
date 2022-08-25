package com.sales.system.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.entity.Order;
import com.sales.system.domain.request.OrderQueryReq;
import com.sales.system.domain.request.OrderUpdateReq;
import com.sales.system.service.OrderManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 订单管理控制层
 * @author: wuyingqiang
 * @date: 2022-08-25 11:15
 */

@Api(value = "订单管理")
@RestController
@RequestMapping("/orderManager")
public class OrderManagerController {

    @Autowired
    private OrderManagerService orderService;

    @ApiModelProperty(value = "获取订单列表")
    @GetMapping("/updateOrderStatus")
    public BaseResult<List<Order>> updateOrderStatus(OrderQueryReq req){
        return BaseResult.ok(orderService.getAllOrderList(req));
    }

    @ApiModelProperty(value = "更新订单")
    @PostMapping("/updateOrderStatus")
    public BaseResult<Integer> updateOrderStatus(OrderUpdateReq req){
        return BaseResult.ok(orderService.updateOrderStatus(req));
    }

}

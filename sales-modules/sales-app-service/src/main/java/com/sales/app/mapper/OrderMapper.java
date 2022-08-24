package com.sales.app.mapper;

import com.sales.app.domain.entity.Order;import com.sales.app.domain.request.IntegralOrderReq;
import com.sales.app.domain.request.OrderQueryReq;
import com.sales.app.domain.response.IntegralOrderResp;import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 获取我的兑换订单列表
     *
     * @param req 兑换请求体
     * @return 兑换订单列表
     */
    List<IntegralOrderResp> selectIntegralOrderList(IntegralOrderReq req);

    /**
     * 获取用户下的礼包订单列表
     * @param userId 用户id
     * @return 礼包待收货的订单列表
     */
    List<Order> selectOrderListByGift(Long userId);

    /**
     * 获取用户下的礼包订单列表
     * @param userId 用户id
     * @return 礼包待付款的订单列表
     */
    List<Order> selectUnpaidGiftOrderList(Long userId);

    /**
     * 获取商城的订单的列表
     *
     * @param req 订单请求类
     * @return 商城订单列表
     */
    List<Order> selectMallOrderList(OrderQueryReq req);
}
package com.sales.app.service;

import com.alipay.api.AlipayApiException;
import com.sales.app.domain.entity.Order;
import com.sales.app.domain.entity.SalesProduct;
import com.sales.app.domain.request.ExchangeIntegralReq;
import com.sales.app.domain.request.GiftMachineReq;
import com.sales.app.domain.request.MachineByMallReq;
import com.sales.app.domain.request.MallProductReq;
import com.sales.app.domain.response.GiftActivityResp;

import java.util.List;

public interface SalesProductService {

    /**
     * 获取商城商品列表
     *
     * @param req 商城商品请求类
     * @return 商品列表
     */
    List<SalesProduct> getProductList(MallProductReq req);

    /**
     * 获取商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    SalesProduct getProductInfo(Long productId);

    /**
     * 获取礼包活动列表
     *
     * @param parentLabel 礼包分类标签
     * @return 礼包列表
     */
    List<GiftActivityResp> getGiftActivityList(String parentLabel);

    /**
     * 获取积分兑换商品列表
     *
     *@param productLabel 商品标签 ：MPOS、电签、旺铺
     * @return 积分兑换商品列表
     */
    List<SalesProduct> getIntegralProducts(String productLabel);

    /**
     * 积分兑换产品
     *
     * @param req 积分兑换请求类
     * @return 结果
     */
    Integer exchangeSalesProduct(ExchangeIntegralReq req);

    /**
     * 购买礼包中的机具
     *
     * @param req 礼包机具请求类
     * @return 结果
     */
    Integer buyGiftMachine(GiftMachineReq req);

    /**
     * 获取用户下的待收货礼包订单列表
     *
     * @param userId 用户id
     * @return 礼包待收货的订单列表
     */
    List<Order> getOrderListByGift(Long userId);

    /**
     * 获取用户下的待付款礼包订单列表
     *
     * @param userId 用户id
     * @return 礼包待付款的订单列表
     */
    List<Order> getUnpaidGiftOrderList(Long userId);

    /**
     * 在商城提交订单购买机具
     *
     * @param req 商城购买请求体
     * @return 结果
     */
    String buyMachineByMall(MachineByMallReq req) throws AlipayApiException;

}

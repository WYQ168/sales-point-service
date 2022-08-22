package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 */
@ApiModel(value = "com-sales-system-domain-entity-Order")
@Data
public class Order {
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 订单类型 gift=礼包订单 integral=积分兑换订单 mall=商城订单
     */
    @ApiModelProperty(value = "订单类型 gift=礼包订单 integral=积分兑换订单 mall=商城订单")
    private String orderType;

    /**
     * 下单商品信息-json串
     */
    @ApiModelProperty(value = "下单商品信息-json串")
    private String goodInfo;

    /**
     * 订单商品总价
     */
    @ApiModelProperty(value = "订单商品总价")
    private BigDecimal totalPrice;

    /**
     * 订单状态 0-待支付 1-待发货 2-待收货 3-已完成
     */
    @ApiModelProperty(value = "订单状态 0-待支付 1-待发货 2-待收货 3-已完成")
    private Integer orderStatus;

    /**
     * 快递单号
     */
    @ApiModelProperty(value = "快递单号")
    private String courierCompany;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 支付方式 1-全款 2-现金+积分 3-现金+mpos
     */
    @ApiModelProperty(value = "支付方式 1-全款 2-现金+积分 3-现金+mpos")
    private Integer payType;

    /**
     * 支付银行卡号
     */
    @ApiModelProperty(value = "支付银行卡号")
    private String payBankCard;

    /**
     * 订单地址id
     */
    @ApiModelProperty(value = "订单地址id")
    private String orderAddressId;

    /**
     * 订单用户名
     */
    @ApiModelProperty(value = "订单用户名")
    private String orderUserName;

    /**
     * 订单手机号
     */
    @ApiModelProperty(value = "订单手机号")
    private String orderUserPhone;

    /**
     * 删除标志 0-未删除 1-已删除
     */
    @ApiModelProperty(value = "删除标志 0-未删除 1-已删除")
    private Integer delFlag;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
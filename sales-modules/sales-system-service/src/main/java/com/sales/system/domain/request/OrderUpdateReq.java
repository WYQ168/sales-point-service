package com.sales.system.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 订单更新请求类
 * @author: wuyingqiang
 * @date: 2022-08-25 14:16
 */
@Data
@ApiModel(value = "订单更新请求类")
public class OrderUpdateReq {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "快递单号")
    private String courierCompany;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
}

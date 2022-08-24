package com.sales.system.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 兑换订单请求体
 * @author: wuyingqiang
 * @date: 2022-08-24 22:08
 */

@Data
@ApiModel(value = "兑换订单请求体")
public class IntegralOrderReq {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "审核的订单状态")
    private Integer orderStatus;
}

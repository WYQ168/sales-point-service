package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 我的积分订单请求体
 * @author: wuyingqiang
 * @date: 2022-08-15 11:48
 */

@Data
@ApiModel(value = "我的积分订单请求体")
public class IntegralOrderReq {

    @ApiModelProperty(value = "兑换类型")
    private String exchangeType;

    @ApiModelProperty(value = "用户id")
    private Long userId;

}

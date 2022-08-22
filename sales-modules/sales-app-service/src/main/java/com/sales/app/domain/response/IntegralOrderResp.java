package com.sales.app.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 积分订单返回体
 * @author: wuyingqiang
 * @date: 2022-08-15 9:58
 */

@Data
@ApiModel(value = "积分订单返回体")
public class IntegralOrderResp {

    @ApiModelProperty(value = "积分商品名字")
    private String orderGoodName;

    @ApiModelProperty(value = "兑换金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "兑换订单状态")
    private Integer OrderStatus;

}

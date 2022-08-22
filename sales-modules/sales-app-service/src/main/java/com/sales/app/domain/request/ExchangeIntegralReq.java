package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 积分兑换请求类
 * @author: wuyingqiang
 * @date: 2022-08-09 22:29
 */
@Data
@ApiModel(value = "积分兑换请求类")
public class ExchangeIntegralReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "兑换数量")
    private Integer exchangeAmount;

    @ApiModelProperty(value = "兑换积分")
    private Integer exchangeIntegral;

    @ApiModelProperty(value = "机具类型")
    private String parentLabel;

}

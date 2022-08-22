package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 交易信息请求体
 * @author: wuyingqiang
 * @date: 2022-08-20 15:51
 */

@Data
@ApiModel(value = "商城交易信息请求体")
public class TradeInfoReq {

    @ApiModelProperty(value = "商户号码")
    private String merId ;

    @ApiModelProperty(value = "商户订单号")
    private String orderId ;

    @ApiModelProperty(value = "订单发送时间，格式为yyyyMMddHHmmss")
    private String txnTime ;

    @ApiModelProperty(value = "交易金额，单位分，不要带小数点")
    private String txnAmt ;

    @ApiModelProperty(value = "付款卡号")
    private String accNo ;
}

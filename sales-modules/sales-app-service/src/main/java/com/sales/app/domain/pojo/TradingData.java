package com.sales.app.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 交易数据实体
 * @author: wuyingqiang
 * @date: 2022-08-17 15:00
 */

@Data
@ApiModel(value = "交易数据实体")
public class TradingData {

    @ApiModelProperty(value = "T0交易")
    private BigDecimal thatDayTrading;

    @ApiModelProperty(value = "T1交易")
    private BigDecimal secondDatTrading;

    @ApiModelProperty(value = "闪付")
    private BigDecimal flashPay;

    @ApiModelProperty(value = "扫码T0")
    private BigDecimal scanCodeThatDay;

    @ApiModelProperty(value = "扫码T1")
    private BigDecimal scanCodeSecondTrading;

    @ApiModelProperty(value = "旺铺交易总额")
    private BigDecimal totalTransaction;
}

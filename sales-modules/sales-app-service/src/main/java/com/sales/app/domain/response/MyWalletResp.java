package com.sales.app.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 我的钱包返回对象
 * @author: wuyingqiang
 * @date: 2022-08-06 14:57
 */

@Data
@ApiModel(value = "我的钱包返回对象")
public class MyWalletResp {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "总可用余额")
    private BigDecimal totalAvailableBalance;

    @ApiModelProperty(value = "结算款账户可用余额")
    private BigDecimal settlementBalance;

    @ApiModelProperty(value = "结算款账户应纳税金额")
    private BigDecimal settlementTax;

    @ApiModelProperty(value = "激活奖励账户可用余额")
    private BigDecimal activationBalance;

    @ApiModelProperty(value = "激活奖励账户应纳税金额")
    private BigDecimal activationTax;

    @ApiModelProperty(value = "旺铺结算可用余额")
    private BigDecimal prosperousBalance;

    @ApiModelProperty(value = "旺铺结算应纳税金额")
    private BigDecimal prosperousTax;

    @ApiModelProperty(value = "旺铺激活可用余额")
    private BigDecimal wpActivationBalance;

    @ApiModelProperty(value = "旺铺激活应纳税金额")
    private BigDecimal wpActivationTax;

}

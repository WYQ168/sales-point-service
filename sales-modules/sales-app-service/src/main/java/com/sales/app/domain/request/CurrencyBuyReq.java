package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 流通券购买请求体
 * @author: wuyingqiang
 * @date: 2022-08-08 11:09
 */

@Data
@ApiModel(value = "流通券购买请求体")
public class CurrencyBuyReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "购买数量")
    private Integer buyAmount;

    @ApiModelProperty(value = "合计价格")
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "支付方式 UnionPay-银联 Alipay-支付宝 weiXin-微信")
    private String payType;

    @ApiModelProperty(value = "银行卡号")
    private String bankCard;

    @ApiModelProperty(value = "使用积分")
    private Integer integralUsed;

}

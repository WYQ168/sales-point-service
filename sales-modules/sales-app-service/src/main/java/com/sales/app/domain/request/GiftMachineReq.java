package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 礼包机具请求类
 * @author: wuyingqiang
 * @date: 2022-08-15 22:51
 */

@ApiModel(value = "礼包机具请求类")
@Data
public class GiftMachineReq {

    @ApiModelProperty(value = "礼包总价")
    private BigDecimal giftAmount;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "礼包名称")
    private String giftName;

    @ApiModelProperty(value = "采购数量")
    private Integer giftQuantity;

    @ApiModelProperty(value = "地址id")
    private String addressId;

    @ApiModelProperty(value = "服务商：总部")
    private String serviceProvider;

    @ApiModelProperty(value = "配送方式（包邮）")
    private String deliveryMethod;

    @ApiModelProperty(value = "支付银行卡号")
    private String payBankCard;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "机具类型")
    private String parentLabel;
}

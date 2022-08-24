package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: mpos现金购买请求类
 * @author: wuyingqiang
 * @date: 2022-08-08 16:54
 */

@Data
@ApiModel(value = "mpos+现金购买请求类")
public class CashPosBuyReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "购买数量")
    private Integer buyAmount;

    @ApiModelProperty(value = "快递单号")
    private String trackingNumber;

    @ApiModelProperty(value = "快递公司")
    private String trackingCompany;

    @ApiModelProperty(value = "机具号")
    private String machineNumber;

    @ApiModelProperty(value = "机具名称")
    private String machineName;

    @ApiModelProperty(value = "寄回数量")
    private Integer sendBackNumber;

    @ApiModelProperty(value = "合计价格")
    private BigDecimal totalMoney;

}

package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 流通账户表
 */
@ApiModel(value = "com-sales-system-domain-entity-CurrencyNote")
@Data
public class CurrencyNote {
    /**
     * 流通券id
     */
    @ApiModelProperty(value = "流通券id")
    private Long currencyId;

    /**
     * 流通券数量
     */
    @ApiModelProperty(value = "流通券数量")
    private Integer currencyNoteNumber;

    /**
     * 可用账户余额
     */
    @ApiModelProperty(value = "可用账户余额")
    private BigDecimal balanceAmount;

    /**
     * 购买者id（userId）
     */
    @ApiModelProperty(value = "购买者id（userId）")
    private Long buyId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
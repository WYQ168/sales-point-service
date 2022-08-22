package com.sales.app.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 收益记录表
 */
@ApiModel(value = "com-sales-app-domain-entity-IncomeRecord")
@Data
public class IncomeRecord {
    /**
     * 收益流水ID
     */
    @ApiModelProperty(value = "收益流水ID")
    private Long incomeId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 收益来源：结算款、激活奖励、旺铺结算、旺铺激活
     */
    @ApiModelProperty(value = "收益来源：结算款、激活奖励、旺铺结算、旺铺激活")
    private String incomeSource;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal incomeAmount;

    /**
     * 账单类型：1-收入 2-支出
     */
    @ApiModelProperty(value = "账单类型：1-收入 2-支出")
    private Integer flowingType;

    /**
     * 收益类型：1-积分 2-现金
     */
    @ApiModelProperty(value = "收益类型：1-积分 2-现金")
    private Integer incomeType;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
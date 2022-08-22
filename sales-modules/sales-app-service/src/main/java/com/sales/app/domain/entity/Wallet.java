package com.sales.app.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 用户钱包表
 */
@ApiModel(value = "com-sales-app-domain-entity-Wallet")
@Data
public class Wallet {
    /**
     * 钱包ID
     */
    @ApiModelProperty(value = "钱包ID")
    private Long walletId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 真实名字
     */
    @ApiModelProperty(value = "真实名字")
    private String realName;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String identityNumber;

    /**
     * 可用余额
     */
    @ApiModelProperty(value = "可用余额")
    private BigDecimal availableBalance;

    /**
     * 通用积分余额
     */
    @ApiModelProperty(value = "通用积分余额")
    private Integer genericIntegral;

    /**
     * 活动积分余额
     */
    @ApiModelProperty(value = "活动积分余额")
    private Integer activityIntegral;

    /**
     * 应纳税金额
     */
    @ApiModelProperty(value = "应纳税金额")
    private BigDecimal taxableAmount;

    /**
     * 可提现金额
     */
    @ApiModelProperty(value = "可提现金额")
    private BigDecimal withdrawAmount;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Long updateBy;
}
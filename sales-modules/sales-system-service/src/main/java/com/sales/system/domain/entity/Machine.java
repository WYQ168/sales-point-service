package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 我的机具表
 */
@ApiModel(value = "com-sales-system-domain-entity-Machine")
@Data
public class Machine {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long machineId;

    /**
     * 产品ID
     */
    @ApiModelProperty(value = "产品ID")
    private Long productId;

    /**
     * 机具类型
     */
    @ApiModelProperty(value = "机具类型")
    private String machineType;

    /**
     * 机具数量
     */
    @ApiModelProperty(value = "机具数量")
    private Integer machineAmount;

    /**
     * 机具编号
     */
    @ApiModelProperty(value = "机具编号")
    private String machineNumber;

    /**
     * 机具状态 0-未激活 1-已激活 2-已绑定 3-未绑定
     */
    @ApiModelProperty(value = "机具状态 0-未激活 1-已激活 2-已绑定 3-未绑定")
    private Integer machineStatus;

    /**
     * 机具绑定者
     */
    @ApiModelProperty(value = "机具绑定者")
    private Long bindingBy;

    /**
     * 机具被绑定时间
     */
    @ApiModelProperty(value = "机具被绑定时间")
    private Date bindingTime;

    /**
     * 机具采购者
     */
    @ApiModelProperty(value = "机具采购者")
    private Long purchaseBy;

    /**
     * 机具采购时间
     */
    @ApiModelProperty(value = "机具采购时间")
    private Date purchaseTime;

    /**
     * 机具激活者
     */
    @ApiModelProperty(value = "机具激活者")
    private Long activationBy;

    /**
     * 机具激活时间
     */
    @ApiModelProperty(value = "机具激活时间")
    private Date activationTime;

    /**
     * 机具换机者
     */
    @ApiModelProperty(value = "机具换机者")
    private Long replacementBy;

    /**
     * 机具换机时间
     */
    @ApiModelProperty(value = "机具换机时间")
    private Date replacementTime;

    /**
     * 机具解绑者
     */
    @ApiModelProperty(value = "机具解绑者")
    private Long unbundlingBy;

    /**
     * 机具解绑时间
     */
    @ApiModelProperty(value = "机具解绑时间")
    private Date unbundlingTime;

    /**
     * 扣费设置
     */
    @ApiModelProperty(value = "扣费设置")
    private String deductionSetting;
}
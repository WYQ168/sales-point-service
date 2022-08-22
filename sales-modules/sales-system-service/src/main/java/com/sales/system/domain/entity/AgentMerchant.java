package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 代理商户表
 */
@ApiModel(value = "com-sales-system-domain-entity-AgentMerchant")
@Data
public class AgentMerchant {
    /**
     * 代理商ID
     */
    @ApiModelProperty(value = "代理商ID")
    private Long agentId;

    /**
     * 会员等级
     */
    @ApiModelProperty(value = "会员等级")
    private String agentLevel;

    /**
     * 商户类型
     */
    @ApiModelProperty(value = "商户类型")
    private Integer agentType;

    /**
     * 会员名字
     */
    @ApiModelProperty(value = "会员名字")
    private String agentName;

    /**
     * 直属伙伴
     */
    @ApiModelProperty(value = "直属伙伴")
    private String directPartner;

    /**
     * 拓展伙伴
     */
    @ApiModelProperty(value = "拓展伙伴")
    private String expandPartner;

    /**
     * 总交易金额
     */
    @ApiModelProperty(value = "总交易金额")
    private BigDecimal transactionAmount;

    /**
     * 绑定机具数量
     */
    @ApiModelProperty(value = "绑定机具数量")
    private Integer bindingQuantity;

    /**
     * 机具剩余数量
     */
    @ApiModelProperty(value = "机具剩余数量")
    private Integer surplusQuantity;

    /**
     * 入网时间
     */
    @ApiModelProperty(value = "入网时间")
    private Date networkAccessTime;
}
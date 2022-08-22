package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 礼包活动表
 */
@ApiModel(value = "com-sales-system-domain-entity-GiftActivity")
@Data
@Accessors(chain = true)
public class GiftActivity {
    /**
     * 礼包id
     */
    @ApiModelProperty(value = "礼包id")
    private String giftId;

    /**
     * 父类标签
     */
    @ApiModelProperty(value = "父类标签")
    private String parentLabel;

    /**
     * 礼包总价
     */
    @ApiModelProperty(value = "礼包总价")
    private BigDecimal giftAmount;

    /**
     * 礼包名称
     */
    @ApiModelProperty(value = "礼包名称")
    private String giftName;

    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量")
    private Integer giftQuantity;

    /**
     * 礼包图片
     */
    @ApiModelProperty(value = "礼包图片")
    private String giftImg;

    /**
     * 礼包简介
     */
    @ApiModelProperty(value = "礼包简介")
    private String giftRemark;

    /**
     * 上架标志
     */
    @ApiModelProperty(value = "上架标志")
    private Integer signsFlag;

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
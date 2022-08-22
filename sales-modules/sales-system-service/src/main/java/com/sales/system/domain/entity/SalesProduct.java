package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * POS机产品表
 */
@ApiModel(value = "com-sales-system-domain-entity-SalesProduct")
@Data
@Accessors(chain = true)
public class SalesProduct {
    /**
     * 产品ID
     */
    @ApiModelProperty(value = "产品ID")
    private Long productId;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String productName;

    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    /**
     * 产品类型 1-POS机具 2-积分兑换商品
     */
    @ApiModelProperty(value = "产品类型 1-POS机具 2-积分兑换商品")
    private Integer productType;

    /**
     * 产品库存
     */
    @ApiModelProperty(value = "产品库存")
    private Integer inventory;

    /**
     * 产品标签
     */
    @ApiModelProperty(value = "产品标签")
    private String productLabel;

    /**
     * 产品序列号SN
     */
    @ApiModelProperty(value = "产品序列号SN")
    private String productSn;

    /**
     * 产品型号
     */
    @ApiModelProperty(value = "产品型号")
    private String productModel;

    /**
     * 产品现金价格
     */
    @ApiModelProperty(value = "产品现金价格")
    private BigDecimal productCashPrice;

    /**
     * 是否可用于积分兑换 0-否 1-可以
     */
    @ApiModelProperty(value = "是否可用于积分兑换 0-否 1-可以")
    private Integer integralFlag;

    /**
     * 产品的积分价格
     */
    @ApiModelProperty(value = "产品的积分价格")
    private BigDecimal productIntegralPrice;

    /**
     * 产品详情，图片url
     */
    @ApiModelProperty(value = "产品详情，图片url")
    private String productDetails;

    /**
     * 产品备注
     */
    @ApiModelProperty(value = "产品备注")
    private String productRemark;

    /**
     * 产品状态 0-未上架 1-已上架
     */
    @ApiModelProperty(value = "产品状态 0-未上架 1-已上架")
    private Integer productStatus;

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
     * 删除标志 0-未删除 1-已删除
     */
    @ApiModelProperty(value = "删除标志 0-未删除 1-已删除")
    private Integer delFlag;

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
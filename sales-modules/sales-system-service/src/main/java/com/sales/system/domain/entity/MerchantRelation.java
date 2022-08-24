package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
    * 商户关系表
    */
@ApiModel(value="com-sales-system-domain-entity-MerchantRelation")
@Data
public class MerchantRelation {
    /**
    * 主键id
    */
    @ApiModelProperty(value="主键id")
    private String relationId;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
    * 商户id
    */
    @ApiModelProperty(value="商户id")
    private Long merchantId;

    /**
    * 商户手机号
    */
    @ApiModelProperty(value="商户手机号")
    private String merchantPhone;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
}
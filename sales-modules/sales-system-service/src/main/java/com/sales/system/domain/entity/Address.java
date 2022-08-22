package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 地址表
 */
@ApiModel(value = "com-sales-system-domain-entity-Address")
@Data
public class Address {
    /**
     * 地址ID
     */
    @ApiModelProperty(value = "地址ID")
    private String addressId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String receiver;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 所在地区
     */
    @ApiModelProperty(value = "所在地区")
    private String addressArea;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String addressInfo;

    /**
     * 是否默认 0-否 1-默认
     */
    @ApiModelProperty(value = " 是否默认 0-否 1-默认")
    private Integer defaultFlag;

    /**
     * 地址定位 0-公司 1-家 2-学校
     */
    @ApiModelProperty(value = "地址定位 0-公司 1-家 2-学校")
    private Integer postion;

    /**
     * 删除标志 0-未删除 1-已删除
     */
    @ApiModelProperty(value = "删除标志 0-未删除 1-已删除")
    private Integer delFlag;

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
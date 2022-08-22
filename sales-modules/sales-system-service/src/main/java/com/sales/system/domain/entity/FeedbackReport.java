package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 意见反馈与投诉表
 */
@ApiModel(value = "com-sales-system-domain-entity-FeedbackReport")
@Data
public class FeedbackReport {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long feedbackId;

    /**
     * 反馈用户ID
     */
    @ApiModelProperty(value = "反馈用户ID")
    private Long userId;

    /**
     * 类型 1-反馈 2-投诉
     */
    @ApiModelProperty(value = "类型 1-反馈 2-投诉")
    private Integer type;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 反馈图片，多张用逗号（,）分割（最多4张）
     */
    @ApiModelProperty(value = "反馈图片，多张用逗号（,）分割（最多4张）")
    private String picture;

    /**
     * 处理类型 如：反馈-功能问题、体验问题
     */
    @ApiModelProperty(value = "处理类型 如：反馈-功能问题、体验问题")
    private Integer processType;

    /**
     * 状态：0=未处理，1=已处理
     */
    @ApiModelProperty(value = "状态：0=未处理，1=已处理")
    private Integer status;

    /**
     * 来源：user=用户
     */
    @ApiModelProperty(value = "来源：user=用户")
    private String reportSource;

    /**
     * 处理结果，处理意见
     */
    @ApiModelProperty(value = "处理结果，处理意见")
    private String handleResult;

    /**
     * 处理时间
     */
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;

    /**
     * 处理人
     */
    @ApiModelProperty(value = "处理人")
    private Long handleBy;

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

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Long updateBy;
}
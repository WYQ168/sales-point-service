package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 文件表
 */
@ApiModel(value = "com-sales-system-domain-entity-FileInfo")
@Data
public class FileInfo {
    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    private String fileId;

    /**
     * 文件类型:   img jpg
     */
    @ApiModelProperty(value = "文件类型:   img jpg ")
    private String fileType;

    /**
     * 文件标签：dict_type
     */
    @ApiModelProperty(value = "文件标签：dict_type")
    private String fileLabel;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String fileUrl;

    /**
     * 上传端口
     */
    @ApiModelProperty(value = "上传端口")
    private String uploadPort;

    /**
     * 使用次数
     */
    @ApiModelProperty(value = "使用次数")
    private Integer usedAmoount;

    /**
     * 删除标志
     */
    @ApiModelProperty(value = "删除标志")
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
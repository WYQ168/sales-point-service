package com.sales.file.domain;

import com.sales.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 文件表
 */
@ApiModel(value = "com-sales-file-domain-FileInfo")
@Data
public class FileInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    private String fileId;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件类型  1-合同  2-banner  3-签名文件 4-印章
     */
    @ApiModelProperty(value = "文件类型, 1-合同, 2-banner, 3-签名文件, 4-印章")
    private Integer type;

    /**
     * 文件地址
     */
    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    /**
     * 上传端口 1-个人用户 2-公司用户
     */
    @ApiModelProperty(value = "上传端口 1-个人用户 2-公司用户")
    private Integer uploadPort;

    /**
     * 删除状态：1删除、0未删除
     */
    @ApiModelProperty(value = "删除状态：1删除、0未删除")
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
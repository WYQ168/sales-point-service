package com.sales.file.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuyingqiang
 * @since 2020-03-25
 */
@Data
@ApiModel(value = "文件实体")
public class FileInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "文件唯一ID")
    private String fileId;
    @ApiModelProperty(value = "文件名")
    private String fileName;
    @ApiModelProperty(value = "是否为图片")
    private Boolean isImg;
    @ApiModelProperty(value = "文件类型")
    private String type;
    @ApiModelProperty(value = "文件大小")
    private Long size;

    /**
     * 物理路径
     */
    @ApiModelProperty(value = "文件物理路径")
    private String path;

    @ApiModelProperty(value = "文件访问地址")
    private String fileUrl;

    @ApiModelProperty(value = "文件来源:fastdfs,aliyun,qiqiu等")
    private String source;

    /**
     * 上传端口 1-个人用户 2-公司用户
     */
    @ApiModelProperty(value = "上传端口 1-个人用户 2-公司用户")
    private Integer uploadPort;

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

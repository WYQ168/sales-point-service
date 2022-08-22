package com.sales.app.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 字典类型表
 */
@ApiModel(value = "com-sales-app-domain-entity-DictType")
@Data
public class DictType {
    /**
     * 字典主键
     */
    @ApiModelProperty(value = "字典主键")
    private String dictId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 层级关系，默认为1。1,2,3,4.....一般不超过9
     */
    @ApiModelProperty(value = "层级关系，默认为1。1,2,3,4.....一般不超过9")
    private Integer dictLevel;

    /**
     * 父级dict_code   如果层级大于1必须要有
     */
    @ApiModelProperty(value = "父级dict_code   如果层级大于1必须要有")
    private String dictParentCode;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String dictDesc;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
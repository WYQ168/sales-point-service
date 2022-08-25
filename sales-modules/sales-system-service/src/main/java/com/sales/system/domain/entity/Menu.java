package com.sales.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 菜单表
 */
@ApiModel(value = "com-sales-system-domain-entity-Menu")
@Data
@TableName("t_menu")
public class Menu {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 是否外链
     */
    @ApiModelProperty(value = "是否外链")
    private Boolean iFrame;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;

    /**
     * 上级菜单ID
     */
    @ApiModelProperty(value = "上级菜单ID")
    private Long pid;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 链接地址
     */
    @ApiModelProperty(value = "链接地址")
    private String path;

    /**
     * 缓存
     */
    @ApiModelProperty(value = "缓存")
    private Boolean cache;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    /**
     * 组件名称
     */
    @ApiModelProperty(value = "组件名称")
    private String componentName;

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限")
    private String permission;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;

    /**
     * 删除状态：1删除、0未删除
     */
    @ApiModelProperty(value = "删除状态：1删除、0未删除")
    private Boolean delFlag;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;
}

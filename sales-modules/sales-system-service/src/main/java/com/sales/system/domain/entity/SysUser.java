package com.sales.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户表
 */
@ApiModel(value = "com-sales-system-domain-entity-SysUser")
@Data
@TableName("t_sys_user")
public class SysUser {
    /**
     * 系统用户id
     */
    @ApiModelProperty(value = "系统用户id")
    private Long sysUserId;

    /**
     * 用户权限
     */
    @ApiModelProperty(value = "用户权限")
    private String sysPermission;

    /**
     * 伙伴等级
     */
    @ApiModelProperty(value = "伙伴等级")
    private Integer partnerLevel;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String sysUsername;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String sysPassword;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String sysPhone;

    /**
     * 真实名字
     */
    @ApiModelProperty(value = "真实名字")
    private String realName;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String identityNumber;

    /**
     * 实名认证状态 0-未认证 1-已认证
     */
    @ApiModelProperty(value = "实名认证状态 0-未认证 1-已认证")
    private Integer verifiedStatus;

    /**
     * 人脸认证状态 0-未认证 1-已认证
     */
    @ApiModelProperty(value = "人脸认证状态 0-未认证 1-已认证")
    private Integer faceAuthentication;

    /**
     * 删除标志 0-未删除 1-删除
     */
    @ApiModelProperty(value = "删除标志 0-未删除 1-删除")
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

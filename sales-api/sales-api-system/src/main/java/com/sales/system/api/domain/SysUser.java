package com.sales.system.api.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户对象 sys_user
 *
 * @author sales
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatarImg;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 状态：1启用、0禁用
     */
    @ApiModelProperty(value = "状态：1启用、0禁用")
    private Short enabled;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private Long deptId;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 删除状态：1删除、0未删除
     */
    @ApiModelProperty(value = "删除状态：1删除、0未删除")
    private Short delFlag;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String name;

    /**
     * 人脸认证状态：1已认证、0未认证
     */
    @ApiModelProperty(value = "人脸认证状态：1已认证、0未认证")
    private Short authenticateFlag;

    /**
     * 最后修改密码的日期
     */
    @ApiModelProperty(value = "最后修改密码的日期")
    private Date lastPasswordResetTime;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /** 法大大用户id*/
    private String fddUserId;

}

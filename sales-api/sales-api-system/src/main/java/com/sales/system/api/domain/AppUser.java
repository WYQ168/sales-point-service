package com.sales.system.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
    * 基础用户表
    */
@ApiModel(value="com-sales-app-domain-entity-AppUser")
@Data
public class AppUser {
    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
    * 登录名
    */
    @ApiModelProperty(value="登录名")
    private String loginName;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名")
    private String userName;

    /**
    * 性别 0-人妖 1-男 2-女
    */
    @ApiModelProperty(value="性别 0-人妖 1-男 2-女")
    private Integer userSex;

    /**
    * 头像
    */
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 手机号
    */
    @ApiModelProperty(value="手机号")
    private String phoneMember;

    /**
    * 银行卡号
    */
    @ApiModelProperty(value="银行卡号")
    private String bankCard;

    /**
    * 开户银行
    */
    @ApiModelProperty(value="开户银行")
    private String openBank;

    /**
    * 身份证号
    */
    @ApiModelProperty(value="身份证号")
    private String identityMember;

    /**
    * 邀请二维码
    */
    @ApiModelProperty(value="邀请二维码")
    private String inviteCode;

    /**
    * 实名认证 0-未认证 1-已认证
    */
    @ApiModelProperty(value="实名认证 0-未认证 1-已认证")
    private Integer verifiedStatus;

    /**
    * 我的推荐码
    */
    @ApiModelProperty(value="我的推荐码")
    private String recommendCode;

    /**
    * 注册时间
    */
    @ApiModelProperty(value="注册时间")
    private Date registerTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
    * 用户状态
    */
    @ApiModelProperty(value="用户状态")
    private String userStatus;

    /**
    * 职级ID
    */
    @ApiModelProperty(value="职级ID")
    private String rank;

    /**
     * 删除标志
     */
    @ApiModelProperty(value="删除标志")
    private String delFlag;

    /**
    * 最后登录IP
    */
    @ApiModelProperty(value="最后登录IP")
    private String lastLoginIp;

    /**
    * 最后一次登录时间
    */
    @ApiModelProperty(value="最后一次登录时间")
    private Date lastLoginTime;

    /**
    * 设备系统类型：android,ios
    */
    @ApiModelProperty(value="设备系统类型：android,ios")
    private String systemType;
}
package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 修改用户信息请求体
 * @author: wuyingqiang
 * @date: 2022-08-10 11:43
 */
@Data
@ApiModel(value = "修改用户信息请求体")
public class UpdateUserInfoReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    private String identityCard;

    @ApiModelProperty(value = "验证码")
    private String smsCode;

    @ApiModelProperty(value = "头像")
    private String avatarImg;

}

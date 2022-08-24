package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 用户信息请求体
 * @author: wuyingqiang
 * @date: 2022-08-05 17:55
 */

@Data
@ApiModel(value = "用户信息请求体")
public class UserInfoReq {

    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

    @ApiModelProperty(value = "伙伴名字")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户ID")
    private Long userId;
}

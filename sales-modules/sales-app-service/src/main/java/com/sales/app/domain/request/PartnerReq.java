package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 我的伙伴请求类
 * @author: wuyingqiang
 * @date: 2022-08-05 17:55
 */

@Data
@ApiModel(value = "我的伙伴请求体")
public class PartnerReq {

    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

    @ApiModelProperty(value = "伙伴名字")
    private String username;
}

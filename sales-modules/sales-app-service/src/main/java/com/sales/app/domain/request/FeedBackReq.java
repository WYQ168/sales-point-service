package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 投诉反馈请求体
 * @author: wuyingqiang
 * @date: 2022-08-11 14:33
 */

@Data
@ApiModel(value = "投诉反馈请求体")
public class FeedBackReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "处理状态 0-未处理 1-已处理")
    private Integer status;
}

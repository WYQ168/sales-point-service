package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 我的积分请求体
 * @author: wuyingqiang
 * @date: 2022-08-11 9:51
 */

@Data
@ApiModel(value = "我的积分")
public class IntegralReq {

    @ApiModelProperty(value = "积分分类")
    private Integer flowingType;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "积分来源常量")
    private List<String> integralSource;
}

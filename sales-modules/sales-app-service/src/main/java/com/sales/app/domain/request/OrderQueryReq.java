package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 订单查询请求类
 * @author: wuyingqiang
 * @date: 2022-08-23 16:51
 */

@Data
@ApiModel(value = "订单查询请求类")
public class OrderQueryReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

}

package com.sales.system.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 订单管理查询类
 * @author: wuyingqiang
 * @date: 2022-08-25 11:40
 */
@Data
@ApiModel(value = "订单管理查询类")
public class OrderQueryReq {

    @ApiModelProperty(value = "订单类型")
    private String orderType;

}

package com.sales.app.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 订单的商品信息类
 * @author: wuyingqiang
 * @date: 2022-08-24 8:54
 */

@Data
@ApiModel(value = "订单的商品信息类")
public class GoodInfo {

    @ApiModelProperty(value = "商品名字")
    private String goodName;

    @ApiModelProperty(value = "商品数量")
    private Integer goodNumber;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal goodPrice;
}

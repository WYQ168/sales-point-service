package com.sales.app.domain.request;

import com.sales.app.domain.entity.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 商城购买的POS请求体
 * @author: wuyingqiang
 * @date: 2022-08-18 9:51
 */

@Data
@ApiModel(value = "商城购买的POS请求体")
public class MachineByMallReq {

    @ApiModelProperty(value = "地址")
    private String addressId;

    @ApiModelProperty(value = "购买商品的数量")
    private Integer machineAmount;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "购买方式：现金支付、流通券支付")
    private String buyType;

    @ApiModelProperty(value = "流通券使用数量")
    private Integer noteAmount;

    @ApiModelProperty(value = "商品名字")
    private String goodName;

}

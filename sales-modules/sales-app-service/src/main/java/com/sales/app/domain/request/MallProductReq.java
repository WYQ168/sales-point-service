package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 商城商品请求类
 * @author: wuyingqiang
 * @date: 2022-08-05 11:16
 */

@Data
@ApiModel(value = "商城商品请求类")
public class MallProductReq {

    @ApiModelProperty(value = "产品类型 0-机具商城 1-积分商城")
    private Integer productType;

    @ApiModelProperty(value = "排序字段 create_time product_price")
    private String sortField;

    @ApiModelProperty(value = "品牌名称")
    private String brand;

}

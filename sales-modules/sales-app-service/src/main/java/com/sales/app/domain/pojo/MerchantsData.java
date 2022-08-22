package com.sales.app.domain.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 商户数据类
 * @author: wuyingqiang
 * @date: 2022-08-16 17:01
 */

@Data
@ApiModel(value = "商户数据类")
@Accessors(chain = true)
public class MerchantsData {

    @ApiModelProperty(value = "商户数量")
    private Integer merchantsNumber;

    @ApiModelProperty(value = "商户类型")
    private String merchantsType;

}

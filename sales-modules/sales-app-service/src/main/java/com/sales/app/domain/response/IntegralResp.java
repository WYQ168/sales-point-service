package com.sales.app.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @description: 我的积分返回体
 * @author: wuyingqiang
 * @date: 2022-08-11 11:03
 */
@Data
@ApiModel(value = "我的积分")
@Accessors(chain = true)
public class IntegralResp {

    @ApiModelProperty(value = "积分数量")
    private BigDecimal integralAmount;

    @ApiModelProperty(value = "积分名字")
    private String integralName;

}

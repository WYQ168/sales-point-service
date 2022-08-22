package com.sales.app.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 礼包活动返回体
 * @author: wuyingqiang
 * @date: 2022-08-09 15:19
 */

@ApiModel(value = "礼包活动信息")
@Data
public class GiftActivityResp {

    @ApiModelProperty(value = "总价")
    private BigDecimal giftAmount;

    @ApiModelProperty(value = "购买数量")
    private Integer buyQuantity;

    @ApiModelProperty(value = "礼包名字")
    private String giftName;

    @ApiModelProperty(value = "礼包图片")
    private String giftImg;

    @ApiModelProperty(value = "礼包备注")
    private String giftRemark;
}

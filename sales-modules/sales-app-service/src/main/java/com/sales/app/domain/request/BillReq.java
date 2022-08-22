package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 账单信息请求体
 * @author: wuyingqiang
 * @date: 2022-08-06 17:24
 */

@Data
@ApiModel(value = "账单信息请求体")
public class BillReq {

    @ApiModelProperty(value = "筛选月份 如：2022-09")
    private String monthTime;

    @ApiModelProperty(value = "账单标签")
    private Integer incomeLabel;

    @ApiModelProperty(value = "用户id")
    private Long userId;
}

package com.sales.app.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 账单记录实体类
 * @author: wuyingqiang
 * @date: 2022-08-06 16:49
 */
@Data
@ApiModel(value = "账单信息")
public class BillResp {

    @ApiModelProperty(value = "账单类型 1-收入 2-支出")
    private Integer flowingType;

    @ApiModelProperty(value = "金额")
    private BigDecimal incomeAmount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}

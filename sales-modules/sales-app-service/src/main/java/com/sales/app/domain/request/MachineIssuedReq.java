package com.sales.app.domain.request;

import com.sales.app.domain.entity.Machine;
import com.sales.app.domain.entity.SalesProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 机具下发请求体
 * @author: wuyingqiang
 * @date: 2022-08-16 15:41
 */

@Data
@ApiModel(value = "机具下发请求体")
public class MachineIssuedReq {

    @ApiModelProperty(value = "下发数量")
    private Integer issuedAmount;

    @ApiModelProperty(value = "下发产品列表")
    private List<Machine> productList;

    @ApiModelProperty(value = "下发指定人：我的商户")
    private Long issuedUserId;

    @ApiModelProperty(value = "用户id")
    private Long userId;
}

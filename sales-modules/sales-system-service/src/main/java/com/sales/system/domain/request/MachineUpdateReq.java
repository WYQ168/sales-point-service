package com.sales.system.domain.request;

import com.sales.system.domain.entity.Machine;
import com.sales.system.domain.entity.SalesProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 机具产品更新请求体
 * @author: wuyingqiang
 * @date: 2022-08-22 9:41
 */

@Data
@ApiModel(value = "机具产品更新请求体")
public class MachineUpdateReq extends SalesProduct {

    @ApiModelProperty(value = "上下架状态")
    private Integer status;

}

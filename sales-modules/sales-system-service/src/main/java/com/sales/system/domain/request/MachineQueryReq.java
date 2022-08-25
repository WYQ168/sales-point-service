package com.sales.system.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 机具查询类
 * @author: wuyingqiang
 * @date: 2022-08-22 11:16
 */

@Data
@ApiModel(value = "机具查询类")
public class MachineQueryReq {

    @ApiModelProperty(value = "机具编号、序列号SN")
    private String productSn;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "机具状态")
    private Integer machineStatus;

}

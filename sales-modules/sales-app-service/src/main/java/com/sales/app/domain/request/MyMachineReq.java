package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 我的机具请求类
 * @author: wuyingqiang
 * @date: 2022-08-10 17:20
 */
@Data
@ApiModel(value ="我的机具请求体")
public class MyMachineReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "机具状态")
    private Integer machineStatus;

    @ApiModelProperty(value = "机具编号")
    private String machineNumber;

    @ApiModelProperty(value = "起始编号")
    private String startNumber;

    @ApiModelProperty(value = "结束编号")
    private String endNumber;

}

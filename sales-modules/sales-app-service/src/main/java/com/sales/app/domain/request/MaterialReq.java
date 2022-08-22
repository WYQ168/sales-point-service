package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 宣传材料请求体
 * @author: wuyingqiang
 * @date: 2022-08-12 15:36
 */
@Data
@ApiModel(value = "宣传材料请求体")
public class MaterialReq {

    @ApiModelProperty(value = "宣传类型 greeting=问候图  poster=海报图")
    private String advertisingType;

    @ApiModelProperty(value = "排序时间 desc asc")
    private String sortType;

    @ApiModelProperty(value = "下载量排序 desc asc")
    private String downType;
}

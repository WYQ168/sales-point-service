package com.sales.app.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 基础查询类
 * @author: wuyingqiang
 * @date: 2022-08-17 15:31
 */

@Data
@ApiModel(value = "基础查询类")
public class BaseQueryReq {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "搜索类型")
    private String searchType;

    @ApiModelProperty(value = "搜索值")
    private String searchValue;

}

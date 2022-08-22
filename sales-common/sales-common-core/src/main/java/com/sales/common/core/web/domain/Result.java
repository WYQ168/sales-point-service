package com.sales.common.core.web.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 5039292329379147046L;

    @ApiModelProperty(value = "错误信息",required = false, dataType = "String")
    private String msg = "";

    @ApiModelProperty(value = "错误代码(正常响应为0)",required = false, dataType = "String")
    private int code = 0;

}
package com.sales.im.domain.entry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(value = "Sms", description = "短信参数")
public class Sms {

    @ApiModelProperty(value = "手机号",position = 3)
    @NotBlank
    private String phone;

    @ApiModelProperty(value = "模板参数 {}",position = 4)
    private String code;

    @ApiModelProperty(value = "收件人姓名，模板参数 {}")
    private String name;

    @ApiModelProperty(value = "短链接，模板参数 {}")
    private String url;
}
package com.sales.app.domain.response;

import com.sales.app.domain.entity.CurrencyNote;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 流通券账户返回体
 * @author: wuyingqiang
 * @date: 2022-08-08 14:24
 */
@Data
@ApiModel(value = "流通券账户返回体")
public class CurrencyNoteResp extends CurrencyNote {

    @ApiModelProperty(value = "积分余额")
    private Integer integral;

}

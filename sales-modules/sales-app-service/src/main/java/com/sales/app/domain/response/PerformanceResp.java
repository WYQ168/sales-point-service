package com.sales.app.domain.response;

import com.sales.app.domain.pojo.MerchantsData;
import com.sales.app.domain.pojo.TradingData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: 业绩返回类
 * @author: wuyingqiang
 * @date: 2022-08-16 17:17
 */

@Data
@ApiModel(value = "业绩返回类")
@Accessors(chain = true)
public class PerformanceResp {

    @ApiModelProperty(value = "商户数据类")
    private List<MerchantsData> newMerchantsList;

    @ApiModelProperty(value = "交易数据")
    private TradingData tradingData;

    @ApiModelProperty(value = "新增伙伴数")
    private Integer newPartnerNumber;

    @ApiModelProperty(value = "新增商户数")
    private Integer newMerchants;
}

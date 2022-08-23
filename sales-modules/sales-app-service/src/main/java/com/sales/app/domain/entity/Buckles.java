package com.sales.app.domain.entity;

import com.sales.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 扣费表 t_buckles
 * 
 * @author zhangtailong
 * @date 2022-08-23
 */
@ApiModel(value = "com-sales-app-domain-entity-Buckles")
@Data
public class Buckles extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 扣费id */
	@ApiModelProperty(value = "扣费id")
	private Long bucklesId;
	/** 产品id */
	@ApiModelProperty(value = "产品id")
	private Long productId;
	/** 扣费金额（ 按照 比例(%) ） */
	@ApiModelProperty(value = "扣费金额")
	private Double bucklesMoney;
	/** 扣费状态( 0 无需扣费 1 已扣费) */
	@ApiModelProperty(value = "扣费状态( 0 无需扣费 1 已扣费)")
	private Integer bucklesStatus;
	/** 备用字段1 */
	@ApiModelProperty(value = "备用字段1")
	private String spareField1;
	/** 备用字段2 */
	@ApiModelProperty(value = "备用字段2")
	private String spareField2;
	/** 备用字段3 */
	@ApiModelProperty(value = "备用字段3")
	private String spareField3;

}

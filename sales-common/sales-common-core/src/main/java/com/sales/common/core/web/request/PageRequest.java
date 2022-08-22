package com.sales.common.core.web.request;

import com.sales.common.core.web.constants.ValidatorConstants;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PageRequest<T> implements BaseRequest,Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -1938782111506143631L;

	/**
	 * 当前页数
	 */
	@Min(value = 1,message = ValidatorConstants.MUST_BE_GREATER_THAN)
	@NotNull(message = ValidatorConstants.IS_NOT_EMPTY)
	private int currentPage = 1;
	
	/**
	 * 每页显示页数
	 */
	@Min(value = 1,message = ValidatorConstants.MUST_BE_GREATER_THAN)
	@NotNull(message = ValidatorConstants.IS_NOT_EMPTY)
	private int pageSize = 10;
	
	/**
	 * 请求参数
	 */
	@Valid
	private T request;
}

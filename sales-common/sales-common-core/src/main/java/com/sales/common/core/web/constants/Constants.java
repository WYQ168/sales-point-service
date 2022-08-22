package com.sales.common.core.web.constants;

/***
 * ===============================
 * 作者：amos lam
 * 时间：2018年12月20日下午7:40:33
 * 备注：全局常亮
 * ===============================
 */
public interface Constants {

	String TRACE_ID_KEY = "X-B3-TraceId";
	
	/**
	 * TraceId key
	 */
	String X_B3_TRACEID_KEY = "X-B3-TraceId";

	/**
	 * 资源不存在
	 */
	Integer NOT_FOUND = 404;
	
	/**
	 * 防重缓存 key 
	 * GET类型
	 */
	String REPEAT_DATA_SESSION_KEY_GET = "GET_REPEAT";
	
	/**
	 * 防重缓存 key  
	 * POST类型
	 */
	String REPEAT_DATA_SESSION_KEY_POST = "POST_REPEAT";

	/**
	 * 防重POST请求
	 */
	String PREVENT_REPEAT_METHOD_POST = "POST";

	/**
	 * 防重GET请求
	 */
	String PREVENT_REPEAT_METHOD_GET = "GET";

	/**
	 * 防重隔断符号
	 */
	String PREVENT_REPEAT_DATA_PARTITION = "_";

	/**
	 * Header用户名key
	 */
	String VFRESH_ADMIN_USERNAME = "VF_USERNAME";
	
	/**
	 * Header用户名key
	 */
	String VF_ADMIN_USER_DETAILS = "VF_ADMIN_USER_DETAILS";
	
	/**
	 * http请求头
	 */
	String HEARD_TRACE_ID = "Trace-Id";
	
	/**
	 * 脱敏过滤字段
	 */
	String[] FILTER_KEYS = {
			"password"
			,"rePassword"
			,"idCard"
			,"phone"
			,"mobile"
		};
}

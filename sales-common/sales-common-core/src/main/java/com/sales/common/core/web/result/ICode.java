package com.sales.common.core.web.result;

import java.io.Serializable;

/**
 * @desc: 编码规则：总长度 为8位：服务编码（2位）+模块编码（3位）+异常编码（3位）
 * 服务编码说明:
 * 10=系统级别异常，占用
 * 11=gateway-service
 * 12=auth-service
 * 13=app-service
 * 14=system-service
 * 15=im-service
 * 16=file-service
 * 17=job-service
 * 18=monitor-service
 * @author wuyingqiang
 * @date 2022/7/26 7:12
 */
public interface ICode extends Serializable{
	/**
	 * 异常码
	 * @return
	 */
	 Integer getCode();

	/**
	 * 异常信息
	 * @return
	 */
	 String getMsg();
}

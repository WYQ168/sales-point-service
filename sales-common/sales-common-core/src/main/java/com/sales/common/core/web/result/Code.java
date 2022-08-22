package com.sales.common.core.web.result;

/**
 * @desc: 系统响应信息，编码规则：总长度 为8位：服务编码（2位）+模块编码（3位）+异常编码（3位）
 * @author wuyingqiang
 * @date 2022/7/26 6:36
 */

public enum Code implements ICode {

	/**0 - 1000 以下为HTTP异常 */
	SUCCESS(200,"操作成功"),
	FAIL(0,"操作失败"),
	ERR_NOT_FOUND(404,"资源不存在"),
	ERR_SYSTEM(500,"服务开小差了"),
	/**10开头为系统*/
	ERR_OPTIONS_FAILED(10001001,"操作失败"),
	ERR_UN_REPEAT(10001002,"请勿频繁操作，%s秒后再重试"),
	ERR_DEAL_DATA_FAILED(10001003,"数据处理异常"),
	ERR_SERVER_GET_EXCEPTION(10001004,"服务调用失败"),
	;
	private Integer code;
	
	private String msg;
	
	@Override
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private Code(Integer code, String msg) {
		
		this.code = code;
		this.msg = msg;
	}
}

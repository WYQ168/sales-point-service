package com.sales.common.core.exception;


import com.sales.common.core.web.result.ICode;

/**
 *@Title:UnRollbackException
 *@Desc: 不需要回滚的异常
 *@Author: wuyingqiang
 *@Date: 2019/3/31 15:29
 */
public class UnRollbackException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570458671984560672L;

	/**
	 * 异常码
	 */
	private Integer errorCode;
	
	/**
	 * 异常信息
	 */
	private String errorMessage;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrormessage() {
		return errorMessage;
	}

	public void setErrormessage(String errormessage) {
		this.errorMessage = errormessage;
	}

	public UnRollbackException(ICode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode.getCode();
		this.errorMessage = errorCode.getMsg();
	}
	
	public UnRollbackException(ICode errorCode, String errMsg, Object... str) {
		super(formatStr(errMsg, str));
		this.errorCode = errorCode.getCode();
		this.errorMessage = formatStr(errMsg, str);
	}
	
	public UnRollbackException(ICode errorCode, String errMsg, Object[] data, Object... str) {
		super(formatStr(errMsg, str));
		this.errorCode = errorCode.getCode();
		this.errorMessage = formatStr(errMsg, str);
	}
	
	public UnRollbackException(ICode errorCode, Object... str) {
		super(formatStr(errorCode.getMsg(), str));
		this.errorCode = errorCode.getCode();
		this.errorMessage = formatStr(errorCode.getMsg(), str);
	}
	
	public UnRollbackException(ICode errorCode, Object[] data, Object... str) {
		super(formatStr(errorCode.getMsg(), str));
		this.errorCode = errorCode.getCode();
		this.errorMessage = formatStr(errorCode.getMsg(), str);
	}
	
	private static String formatStr(String errMsg,Object... str) {
	    if(str.length > 0) {
            
            return String.format(errMsg, str);
        }
	    return errMsg;
	}

	public UnRollbackException() {
		super();
	}
	
}

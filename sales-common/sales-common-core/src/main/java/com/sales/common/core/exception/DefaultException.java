package com.sales.common.core.exception;


import com.sales.common.core.web.result.ICode;

/**
 * @Title:DefaultException
 * @Desc: 默认异常，其他所有异常扩展 都需要继承此类
 * @Author: wuyingqiang
 * @Date: 2019-3-20 20:59
 */
abstract class DefaultException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -1570458671984560672L;

    /**
     * 异常码
     */
    protected Integer errorCode;

    /**
     * 异常信息
     */
    protected String errorMessage;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DefaultException(ICode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMsg();
    }

    public DefaultException(ICode errorCode, String errMsg,Object... str) {
        super(formatStr(errMsg, str));
        this.errorCode = errorCode.getCode();
        this.errorMessage = formatStr(errMsg, str);
    }

    public DefaultException(ICode errorCode, String errMsg,Object[] data,Object... str) {
        super(formatStr(errMsg, str));
        this.errorCode = errorCode.getCode();
        this.errorMessage = formatStr(errMsg, str);
    }

    public DefaultException(ICode errorCode, Object... str) {
        super(formatStr(errorCode.getMsg(), str));
        this.errorCode = errorCode.getCode();
        this.errorMessage = formatStr(errorCode.getMsg(), str);
    }

    public DefaultException(ICode errorCode,Object[] data, Object... str) {
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

    public DefaultException() {
        super();
    }
}

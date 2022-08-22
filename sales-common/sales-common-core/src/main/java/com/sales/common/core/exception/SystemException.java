package com.sales.common.core.exception;


import com.sales.common.core.web.result.ICode;

/**
 *@Title:SystemException
 *@Desc: 系统异常
 *@Author: wuyingqiang
 *@Date: 2019-3-20 21:03
 */
public class SystemException extends DefaultException{
    public SystemException(ICode errorCode) {
        super(errorCode);
    }

    public SystemException(ICode errorCode, String errMsg, Object... str) {
        super(errorCode, errMsg, str);
    }

    public SystemException(ICode errorCode, String errMsg, Object[] data, Object... str) {
        super(errorCode, errMsg, data, str);
    }

    public SystemException(ICode errorCode, Object... str) {
        super(errorCode, str);
    }

    public SystemException(ICode errorCode, Object[] data, Object... str) {
        super(errorCode, data, str);
    }

    public SystemException() {
        super();
    }
}

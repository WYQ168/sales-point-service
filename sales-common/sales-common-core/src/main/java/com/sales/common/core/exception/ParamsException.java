package com.sales.common.core.exception;


import com.sales.common.core.web.result.ICode;

/**
 *@Title:ParamsException
 *@Desc: 参数异常
 *@Author: wuyingqiang
 *@Date: 2019-3-20 20:46
 */
public class ParamsException extends DefaultException{
    public ParamsException(ICode errorCode) {
        super(errorCode);
    }

    public ParamsException(ICode errorCode, String errMsg, Object... str) {
        super(errorCode, errMsg, str);
    }

    public ParamsException(ICode errorCode, String errMsg, Object[] data, Object... str) {
        super(errorCode, errMsg, data, str);
    }

    public ParamsException(ICode errorCode, Object... str) {
        super(errorCode, str);
    }

    public ParamsException(ICode errorCode, Object[] data, Object... str) {
        super(errorCode, data, str);
    }

    public ParamsException() {
    }
}

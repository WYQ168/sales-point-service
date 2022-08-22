package com.sales.common.core.exception;


import com.sales.common.core.web.result.ICode;

/**
 *@Title:BusinessException
 *@Desc: 业务异常
 *@Author: wuyingqiang
 *@Date: 2019-3-20 21:00
 */
public class BusinessException extends DefaultException{
    public BusinessException(ICode errorCode) {
        super(errorCode);
    }

    public BusinessException(ICode errorCode, String errMsg, Object... str) {
        super(errorCode, errMsg, str);
    }

    public BusinessException(ICode errorCode, String errMsg, Object[] data, Object... str) {
        super(errorCode, errMsg, data, str);
    }

    public BusinessException(ICode errorCode, Object... str) {
        super(errorCode, str);
    }

    public BusinessException(ICode errorCode, Object[] data, Object... str) {
        super(errorCode, data, str);
    }
}

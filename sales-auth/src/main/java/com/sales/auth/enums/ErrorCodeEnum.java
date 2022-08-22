package com.sales.auth.enums;

import com.sales.common.core.web.result.ICode;

/**
 * @desc: 认证异常
 * @author wuyingqiang
 * @date 2022/8/5 17:51
 */
public enum ErrorCodeEnum implements ICode {
    ERR_LOGIN_TYPE_EMPTY(12001001,"登陆方式不能为空"),
    ERR_LOGIN_USER_TYPE_EMPTY(12001002,"用户类型不能为空"),
    ERR_LOGIN_TYPE(12001003, "不支持此种登陆方式"),
    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

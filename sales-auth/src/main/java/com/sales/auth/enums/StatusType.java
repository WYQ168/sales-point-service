package com.sales.auth.enums;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/13 16:53
 */
public enum StatusType {
    UNENABLE( (short) 0,"未启用"),
    ENABLE( (short) 1,"启用");
    Short code;
    String type;
     StatusType(Short code, String type){
        this.type=type;
        this.code=code;
    }

    public String getType() {
        return type;
    }

    public Short getCode() {
        return code;
    }

}

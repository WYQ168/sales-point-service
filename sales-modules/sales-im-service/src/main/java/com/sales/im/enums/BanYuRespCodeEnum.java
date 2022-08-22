package com.sales.im.enums;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/7/22 20:17
 */
public enum BanYuRespCodeEnum {
    COIN_NOT_ENOUGH(20001,"鱼币不足"),
     PULLED_BLACK(20002,"被拉黑"),
    SENSITIVE_WORD(20003,"敏感字"),
    ;

    private Integer code;
    private String desc;
    BanYuRespCodeEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

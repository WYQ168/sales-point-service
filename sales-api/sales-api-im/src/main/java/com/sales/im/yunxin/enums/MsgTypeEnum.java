package com.sales.im.yunxin.enums;
/**
 * @desc: 云信消息类型
 * @author wuyingqiang
 * @date 2022/8/3 21:11
 */
public enum MsgTypeEnum {
    TEXT( "TEXT"),
    PICTURE("PICTURE"),
    AUDIO("AUDIO"),
    VIDEO("VIDEO"),
    LOCATION("LOCATION"),
    NOTIFICATION("NOTIFICATION"),
    FILE("FILE"),
    TIPS("TIPS"),
    CUSTOM("CUSTOM"),
    ;

    private String value;
    MsgTypeEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

}

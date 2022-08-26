package com.sales.system.enums;

public enum SystemEnum {

    DELETE_STATUS_1("未删除","0"),
    DELETE_STATUS_2("已删除","1"),
    USER_TYPE_1("移动端用户","mobile"),
    USER_TYPE_2("管理端用户","manage"),
    USER_TYPE_MOBILE("普通用户","1"),
    USER_TYPE_MANAGE("系统用户","2"),
    AUTHENTICATE_FLAG_ON("已认证","1"),
    AUTHENTICATE_FLAG_NOT("未认证","0"),
    SYSTEM_CONFIG_PREFIX("系统配置redis前缀","system_config:");

    private final String label;
    private final String value;

    SystemEnum(String label, String value){
        this.label=label;
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}

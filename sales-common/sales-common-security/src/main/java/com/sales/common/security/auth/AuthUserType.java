package com.sales.common.security.auth;

/**
 * 认证用户类型
 * 
 * @author sales
 */
public enum AuthUserType
{
    /**
     * 喔刷ProAPP
     */
    WSPRO("other"),

    /**
     * 喔刷商家版
     */
    WSMERCAHNT("other"),

    /**
     * 后台用户
     */
    MANAGE("manage"),

    /**
     * 手机端用户
     */
    MOBILE("mobile");
    private String type;
     AuthUserType(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

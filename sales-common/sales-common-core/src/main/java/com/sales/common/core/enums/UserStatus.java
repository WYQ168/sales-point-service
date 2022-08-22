package com.sales.common.core.enums;

/**
 * 用户状态
 * 
 * @author sales
 */
public enum UserStatus
{
    OK(2, "正常"), DISABLE(2, "停用"), DELETED(1, "删除");

    private final Integer code;
    private final String info;

    UserStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}

package com.sales.common.core.enums;

/**
 * 用户状态
 * 
 * @author sales
 */
public enum UserType
{
    SUPER("0", "超级"),
    SYSTEM("1", "系统"),
    NORMAL("2", "普通");

    private final String code;
    private final String type;

    UserType(String code, String type)
    {
        this.code = code;
        this.type = type;
    }

    public String getCode()
    {
        return code;
    }

    public String getType()
    {
        return type;
    }
}

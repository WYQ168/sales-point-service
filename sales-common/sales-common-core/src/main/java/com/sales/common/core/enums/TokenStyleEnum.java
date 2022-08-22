package com.sales.common.core.enums;

/**
 * @desc: 令牌风格
 * @author wuyingqiang
 * @date 2022/7/12 12:29
 */
public enum TokenStyleEnum
{
    UUID("uuid"), SIMPLE_UUID("simple-uuid"), RANDOM_32("random-32"), RANDOM_64("random-64"), RANDOM_128("random-128");
    private final String typeName;
    TokenStyleEnum(String typeName)
    {
        this.typeName = typeName;
    }
    public String getTypeName()
    {
        return typeName;
    }

}

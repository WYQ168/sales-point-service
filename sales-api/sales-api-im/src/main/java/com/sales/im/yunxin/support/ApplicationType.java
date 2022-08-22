package com.sales.im.yunxin.support;

/**
 * @desc:交互数据格式类型定义
 * @author wuyingqiang
 * @date 2022/7/4 17:52
 */
public enum ApplicationType {

    JSON("application/json"), XML("application/xml"), TEXT("text/xml"), FORM("application/x-www-form-urlencoded");

    private String type;

    private ApplicationType(String type) {
        this.type = type;
    }

    public String val() {
        return type;
    }

}

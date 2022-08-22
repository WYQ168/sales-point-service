package com.sales.common.core.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能标志枚举类
 */
public enum FunctionModuleEnum {
    sales("000001", "发起签约"),
    CONTRACT("000002", "合同管理"),
    TEMPLATE("000003", "模板管理"),
    SEAL("000004", "印章管理"),
    ORGANIZATION("000005", "组织架构");

    private final String moduleName;
    private final String moduleCode;

    FunctionModuleEnum( String moduleCode, String moduleName)
    {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public String getModuleCode()
    {
        return moduleCode;
    }

    public static List<String> getAllModuleName(){
        List<String> result = new ArrayList<>();
        FunctionModuleEnum[] values = FunctionModuleEnum.values();
        for (FunctionModuleEnum value : values) {
            result.add(value.getModuleName());
        }
        return result;
    }
}

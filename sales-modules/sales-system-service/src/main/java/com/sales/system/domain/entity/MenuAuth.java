package com.sales.system.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 菜单权限表
    */
@ApiModel(value="com-sales-system-domain-entity-MenuAuth")
@Data
public class MenuAuth {
    /**
    * 主键id
    */
    @ApiModelProperty(value="主键id")
    private Long authId;

    /**
    * 权限 function_key: admin
    */
    @ApiModelProperty(value="权限 function_key: admin")
    private String authKey;

    /**
    * 菜单id
    */
    @ApiModelProperty(value="菜单id")
    private Long menuId;
}
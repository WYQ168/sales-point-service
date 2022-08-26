package com.sales.app.domain.entity;

import com.sales.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 系统配置，以JSON格式记录常用配置，如阿里云，微信等表 t_sys_config
 *
 * @author zhangtailong
 * @date 2022-08-26
 */
@ApiModel(value = "com-sales-app-domain-entity-SysConfig")
@Data
public class SysConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 配置编号 */
	private Long id;
	/** 配置名称 */
	private String name;
	/** 配置内容(JSON) */
	private String value;
	/** 配置注释#用于详细说明整个JSON配置文件 */
	private String comment;
	/** 中文说明 */
	private String description;
	/** 允许外部访问#是否允许外部直接访问此记录，默认为0，不允许 */
	private Integer outsideAccess;
	/** 版本号#配置版本号 */
	private Integer version;
	/** 是否可用#配置是否可用 */
	private Integer isActive;
}

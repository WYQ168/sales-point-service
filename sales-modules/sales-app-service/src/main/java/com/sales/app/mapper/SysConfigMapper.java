package com.sales.app.mapper;

import com.sales.app.domain.entity.SysConfig;

import java.util.List;

/**
 * 系统配置，以JSON格式记录常用配置，如阿里云，微信等 数据层
 *
 * @author zhangtailong
 * @date 2022-08-26
 */
public interface SysConfigMapper
{
	/**
     * 查询系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     *
     * @param id 系统配置，以JSON格式记录常用配置，如阿里云，微信等ID
     * @return 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     */
	public SysConfig selectSysConfigById(Long id);

	/**
     * 查询系统配置，以JSON格式记录常用配置，如阿里云，微信等列表
     *
     * @param sysConfig 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     * @return 系统配置，以JSON格式记录常用配置，如阿里云，微信等集合
     */
	public List<SysConfig> selectSysConfigList(SysConfig sysConfig);

	/**
     * 新增系统配置，以JSON格式记录常用配置，如阿里云，微信等
     *
     * @param sysConfig 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     * @return 结果
     */
	public int insertSysConfig(SysConfig sysConfig);

	/**
     * 修改系统配置，以JSON格式记录常用配置，如阿里云，微信等
     *
     * @param sysConfig 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     * @return 结果
     */
	public int updateSysConfig(SysConfig sysConfig);

	/**
     * 删除系统配置，以JSON格式记录常用配置，如阿里云，微信等
     *
     * @param id 系统配置，以JSON格式记录常用配置，如阿里云，微信等ID
     * @return 结果
     */
	public int deleteSysConfigById(Long id);

	/**
     * 批量删除系统配置，以JSON格式记录常用配置，如阿里云，微信等
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysConfigByIds(String[] ids);

}

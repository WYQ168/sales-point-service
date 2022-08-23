package com.sales.app.mapper;

import com.sales.app.domain.entity.Buckles;

import java.util.List;

/**
 * 扣费 数据层
 * 
 * @author zhangtailong
 * @date 2022-08-23
 */
public interface BucklesMapper 
{
	/**
     * 查询扣费信息
     * 
     * @param bucklesId 扣费ID
     * @return 扣费信息
     */
	public Buckles selectBucklesById(Long bucklesId);
	
	/**
     * 查询扣费列表
     * 
     * @param buckles 扣费信息
     * @return 扣费集合
     */
	public List<Buckles> selectBucklesList(Buckles buckles);
	
	/**
     * 新增扣费
     * 
     * @param buckles 扣费信息
     * @return 结果
     */
	public int insertBuckles(Buckles buckles);
	
	/**
     * 修改扣费
     * 
     * @param buckles 扣费信息
     * @return 结果
     */
	public int updateBuckles(Buckles buckles);
	
	/**
     * 删除扣费
     * 
     * @param bucklesId 扣费ID
     * @return 结果
     */
	public int deleteBucklesById(Long bucklesId);
	
	/**
     * 批量删除扣费
     * 
     * @param bucklesIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBucklesByIds(String[] bucklesIds);
	
}
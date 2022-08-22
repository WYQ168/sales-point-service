package com.sales.file.service;

import com.sales.file.domain.FileInfo;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author lvwaidong
 * @date 2021-12-26
 */
public interface IFileInfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public FileInfo selectFileInfoById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fileInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param fileInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertFileInfo(FileInfo fileInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param fileInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateFileInfo(FileInfo fileInfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteFileInfoByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFileInfoById(String id);
}

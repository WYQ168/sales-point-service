package com.sales.file.service.impl;

import com.sales.file.domain.FileInfo;
import com.sales.file.mapper.FileInfoMapper;
import com.sales.file.service.IFileInfoService;
import com.sales.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author lvwaidong
 * @date 2021-12-26
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService 
{
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public FileInfo selectFileInfoById(String id)
    {
        return fileInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param fileInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo)
    {
        return fileInfoMapper.selectFileInfoList(fileInfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param fileInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFileInfo(FileInfo fileInfo)
    {
        fileInfo.setCreateTime(DateUtils.getNowDate());
        return fileInfoMapper.insertSelective(fileInfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param fileInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFileInfo(FileInfo fileInfo)
    {
        fileInfo.setUpdateTime(DateUtils.getNowDate());
        return fileInfoMapper.updateByPrimaryKeySelective(fileInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFileInfoByIds(String[] ids)
    {
        return fileInfoMapper.deleteFileInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFileInfoById(String id)
    {
        return fileInfoMapper.deleteByPrimaryKey(id);
    }
}

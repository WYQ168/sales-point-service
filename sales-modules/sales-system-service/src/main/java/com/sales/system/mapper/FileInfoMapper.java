package com.sales.system.mapper;

import com.sales.system.domain.entity.FileInfo;

public interface FileInfoMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}
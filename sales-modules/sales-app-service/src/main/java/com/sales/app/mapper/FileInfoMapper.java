package com.sales.app.mapper;

import com.sales.app.domain.entity.FileInfo;
import com.sales.app.domain.request.MaterialReq;

import java.util.List;

public interface FileInfoMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    /**
     * 获取宣传材料数据
     * @param req 宣传材料请求体
     * @return 宣传材料文件
     */
    List<FileInfo> selectFileListByCommunity(MaterialReq req);
}
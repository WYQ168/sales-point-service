package com.sales.app.service.impl;

import com.sales.app.domain.entity.FileInfo;
import com.sales.app.domain.request.MaterialReq;
import com.sales.app.mapper.FileInfoMapper;
import com.sales.app.service.CommunityService;
import com.sales.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 社区业务层
 * @author: wuyingqiang
 * @date: 2022-08-12 15:19
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public List<FileInfo> getAdvertisingMaterial(MaterialReq req) {
        List<FileInfo> fileInfoList = fileInfoMapper.selectFileListByCommunity(req);
        return fileInfoList;
    }
}

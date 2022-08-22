package com.sales.app.service;

import com.sales.app.domain.entity.FileInfo;
import com.sales.app.domain.request.MaterialReq;

import java.util.List;

public interface CommunityService {

    /**
     * 获取宣传材料数据
     * @param req 宣传材料请求体
     * @return 宣传材料
     */
    List<FileInfo> getAdvertisingMaterial(MaterialReq req);
}

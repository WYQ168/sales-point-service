package com.sales.app.controller;

import com.sales.app.domain.entity.FileInfo;
import com.sales.app.domain.request.MaterialReq;
import com.sales.app.service.CommunityService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 宣传材料控制层
 * @author: wuyingqiang
 * @date: 2022-08-12 15:02
 */

@Api(value = "")
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @ApiOperation(value = "获取宣传材料数据")
    @GetMapping("/getAdvertisingMaterial")
    public BaseResult<List<FileInfo>> getAdvertisingMaterial(MaterialReq req){
        return BaseResult.ok(communityService.getAdvertisingMaterial(req));
    }
}

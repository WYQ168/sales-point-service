package com.sales.app.controller;

import com.sales.app.domain.request.BaseQueryReq;
import com.sales.app.domain.response.PerformanceResp;
import com.sales.app.service.MachineService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 业绩控制层
 * @author: wuyingqiang
 * @date: 2022-08-16 16:25
 */
@Api(value = "业绩相关接口")
@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private MachineService machineService;

    @ApiOperation(value = "获取我的个人业绩数据")
    @GetMapping("/getPersonalPerformance")
    public BaseResult<PerformanceResp> getPersonalPerformance(BaseQueryReq req){
        return BaseResult.ok(machineService.getPersonalPerformance(req));
    }

    @ApiOperation(value = "获取我的伙伴业绩数据")
    @GetMapping("/getPartnerPerformance")
    public BaseResult<PerformanceResp> getPartnerPerformance(BaseQueryReq req){
        return BaseResult.ok(machineService.getPartnerPerformance(req));
    }
}

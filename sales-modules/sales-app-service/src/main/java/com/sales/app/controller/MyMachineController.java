package com.sales.app.controller;

import com.sales.app.domain.entity.Machine;
import com.sales.app.domain.request.MachineIssuedReq;
import com.sales.app.domain.request.MyMachineReq;
import com.sales.app.service.MachineService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 我的机具控制层
 * @author: wuyingqiang
 * @date: 2022-08-10 23:26
 */

@RestController
@RequestMapping("/myMachine")
@Api(value = "我的机具控制层")
public class MyMachineController {
    @Autowired
    private MachineService machineService;

    @GetMapping("/getMyMachineList")
    @ApiOperation(value = "获取我的机具列表")
    public BaseResult<List<Machine>> getMyMachineList(MyMachineReq req){
        return BaseResult.ok(machineService.getMyMachineList(req));
    }

    @PostMapping("/machineIssued")
    @ApiOperation(value = "我的机具-下发")
    public BaseResult<Integer> machineIssued(MachineIssuedReq req){
        return BaseResult.ok(machineService.machineIssued(req));
    }
}

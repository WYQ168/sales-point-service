package com.sales.system.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.entity.GiftActivity;
import com.sales.system.domain.entity.Machine;
import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.request.MachineQueryReq;
import com.sales.system.domain.request.MachineUpdateReq;
import com.sales.system.service.MachineManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 机具管理控制层
 * @author: wuyingqiang
 * @date: 2022-08-22 11:25
 */

@Api(value = "机具管理接口")
@RestController
@RequestMapping("/machineManager")
public class MachineManagerController {

    @Autowired
    private MachineManagerService machineManagerService;

    @ApiOperation(value = "查询机具列表")
    @GetMapping("/selectMachineList")
    public BaseResult<List<Machine>> selectMachineList(MachineQueryReq req){
        return BaseResult.ok(machineManagerService.selectAllByCondition(req));
    }

    @ApiOperation(value = "添加礼包")
    @PostMapping("/addActivityGift")
    public BaseResult<Integer> addActivityGift(@RequestBody GiftActivity gift){
        return BaseResult.ok(machineManagerService.addActivityGift(gift));
    }

    @ApiOperation(value = "礼包列表")
    @GetMapping("/getGiftList")
    public BaseResult<List<GiftActivity>> getGiftList(){
        return BaseResult.ok(machineManagerService.getGiftList());
    }

}

package com.sales.system.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.request.MachineUpdateReq;
import com.sales.system.service.MachineManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商城管理控制层
 * @author: wuyingqiang
 * @date: 2022-08-25 10:51
 */

@Api(value = "商城管理")
@RestController
@RequestMapping("/mallManager")
public class MallManagerController {

    @Autowired
    private MachineManagerService machineManagerService;

    @ApiOperation(value = "添加商城商品")
    @PostMapping("/addSalesProduct")
    public BaseResult<Integer> addSalesProduct(@RequestBody SalesProduct salesProduct){
        return BaseResult.ok(machineManagerService.addSalesProduct(salesProduct));
    }

    @ApiOperation(value = "修改机具状态/更新、删除")
    @PostMapping("/updateProductStatus")
    public BaseResult<Integer> updateProductStatus(@RequestBody MachineUpdateReq req){
        return BaseResult.ok(machineManagerService.updateProductStatus(req));
    }

}

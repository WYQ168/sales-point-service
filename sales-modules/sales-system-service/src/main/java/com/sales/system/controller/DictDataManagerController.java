package com.sales.system.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.entity.DictData;
import com.sales.system.domain.entity.DictType;
import com.sales.system.service.DictDataManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 数据字段管理控制层
 * @author: wuyingqiang
 * @date: 2022-08-22 11:36
 */

@Api(value = "数据字典接口")
@RestController
@RequestMapping(value = "/dictDataManager")
public class DictDataManagerController {

    @Autowired
    private DictDataManagerService dictDataManagerService;

    @ApiOperation(value = "添加数据结构")
    @PostMapping("/addDictType")
    public BaseResult<Integer> addDictType(DictType type){
        return BaseResult.ok(dictDataManagerService.addDictType(type));
    }

    @ApiOperation(value = "获取数据结构列表")
    @GetMapping("/getDictTypeList")
    public BaseResult<List<DictType>> getDictTypeList(){
        return BaseResult.ok(dictDataManagerService.getDictTypeList());
    }

    @ApiOperation(value = "更新数据结构")
    @PostMapping("/updateDictType")
    public BaseResult<Integer> updateDictType(DictType type){
        return BaseResult.ok(dictDataManagerService.updateDictType(type));
    }

    @ApiOperation(value = "删除数据结构")
    @PostMapping("/delDictType")
    public BaseResult<Integer> delDictType(String typeId){
        return BaseResult.ok(dictDataManagerService.delDictType(typeId));
    }

    @ApiOperation(value = "新增数据字典")
    @PostMapping("/addDictData")
    public BaseResult<Integer> addDictData(DictData data){
        return BaseResult.ok(dictDataManagerService.addDictData(data));
    }

    @ApiOperation(value = "获取字典数据列表")
    @GetMapping("/getDictDataList")
    public BaseResult<List<DictData>> getDictDataList(String dictType){
        return BaseResult.ok(dictDataManagerService.getDictDataList(dictType));
    }

    @ApiOperation(value = "更新字典数据")
    @PostMapping("/updateDictData")
    public BaseResult<Integer> updateDictData(DictData data){
        return BaseResult.ok(dictDataManagerService.updateDictData(data));
    }

    @ApiOperation(value = "删除字典数据")
    @PostMapping("/delDictData")
    public BaseResult<Integer> delDictData(String dataId){
        return BaseResult.ok(dictDataManagerService.delDictData(dataId));
    }

}

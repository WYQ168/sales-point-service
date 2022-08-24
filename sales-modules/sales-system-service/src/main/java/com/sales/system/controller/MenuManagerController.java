package com.sales.system.controller;

import com.sales.common.core.domain.BaseResult;
import com.sales.system.domain.entity.Menu;
import com.sales.system.service.MenuManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 菜单控制层
 * @author: wuyingqiang
 * @date: 2022-08-24 15:39
 */

@RestController
@RequestMapping("/menu")
public class MenuManagerController {

    @Autowired
    private MenuManagerService menuService;

    @RequestMapping(value = "/getMenuDataList", method = RequestMethod.GET)
    @ApiOperation(value = "获取菜单列表")
    public BaseResult<?> getMenuDataList() {
        return BaseResult.ok(menuService.getMenuDataList());
    }

    @RequestMapping(value = "/addMenuData", method = RequestMethod.POST)
    @ApiOperation(value = "新增菜单信息")
    public BaseResult<?> addMenuData(@RequestBody Menu menu) {
        menuService.addMenuData(menu);
        return BaseResult.ok();
    }

    @RequestMapping(value = "/editMenuData", method = RequestMethod.POST)
    @ApiOperation(value = "编辑菜单信息")
    public BaseResult<?> editMenuData(@RequestBody Menu menu) {
        menuService.editMenuData(menu);
        return BaseResult.ok();
    }
}

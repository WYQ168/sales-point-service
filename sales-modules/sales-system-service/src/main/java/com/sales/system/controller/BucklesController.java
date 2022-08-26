package com.sales.system.controller;

import com.sales.common.core.web.controller.BaseController;
import com.sales.common.core.web.domain.AjaxResult;
import com.sales.common.core.web.page.TableDataInfo;
import com.sales.system.domain.entity.Buckles;
import com.sales.system.service.IBucklesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 扣费 信息操作处理
 *
 * @author zhangtailong
 * @date 2022-08-23
 */
@Api(value = "扣费管理相关接口")
@RestController
@RequestMapping("/buckles")
public class BucklesController extends BaseController {
    @Autowired
    private IBucklesService bucklesService;

    /**
     * 查询扣费列表
     */
    @ApiOperation(value = "扣费列表")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Buckles buckles) {
        startPage();
        List<Buckles> list = bucklesService.selectBucklesList(buckles);
        return getDataTable(list);
    }

    /**
     * 新增保存扣费
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Buckles buckles) {
        return toAjax(bucklesService.insertBuckles(buckles));
    }


    /**
     * 修改保存扣费
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Buckles buckles) {
        return toAjax(bucklesService.updateBuckles(buckles));
    }

    /**
     * 删除扣费
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bucklesService.deleteBucklesByIds(ids));
    }

}

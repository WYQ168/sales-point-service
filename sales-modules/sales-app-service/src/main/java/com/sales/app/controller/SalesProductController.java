package com.sales.app.controller;

import com.sales.app.domain.entity.Order;
import com.sales.app.domain.entity.SalesProduct;
import com.sales.app.domain.request.GiftMachineReq;
import com.sales.app.domain.request.MachineByMallReq;
import com.sales.app.domain.request.MallProductReq;
import com.sales.app.domain.response.GiftActivityResp;
import com.sales.app.service.SalesProductService;
import com.sales.common.core.domain.BaseResult;
import com.sales.common.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: POS机产品控制层
 * @author: wuyingqiang
 * @date: 2022-08-05 11:09
 */

@Api(value = "POS机相关接口")
@RestController
@RequestMapping(value = "/salesProduct")
public class SalesProductController extends BaseController {
    @Autowired
    private SalesProductService salesProductService;

    @ApiOperation(value = "礼包活动列表信息")
    @GetMapping("/getSalesProductInfo")
    public BaseResult<List<GiftActivityResp>> getGiftActivityList(@RequestParam("parentLabel") String parentLabel){
        return BaseResult.ok(salesProductService.getGiftActivityList(parentLabel));
    }

    @ApiOperation(value = "在礼包中采购机具")
    @PostMapping("/buyGiftMachine")
    public BaseResult<Integer> buyGiftMachine(@RequestBody GiftMachineReq req){
        return BaseResult.ok(salesProductService.buyGiftMachine(req));
    }

}

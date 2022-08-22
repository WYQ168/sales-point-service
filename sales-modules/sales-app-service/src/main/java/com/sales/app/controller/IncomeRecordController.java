package com.sales.app.controller;

import com.sales.app.domain.request.BillReq;
import com.sales.app.domain.response.BillResp;
import com.sales.app.service.IncomeRecordService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 收益/账单控制层
 * @author: wuyingqiang
 * @date: 2022-08-06 17:13
 */

@Api(value = "账单收益接口")
@RestController
@RequestMapping("/income")
public class IncomeRecordController {

    @Autowired
    private IncomeRecordService incomeRecordService;

    @ApiOperation(value = "账单记录")
    @GetMapping("/getBillList")
    public BaseResult<List<BillResp>> getBillList(BillReq req){
        return BaseResult.ok(incomeRecordService.getBillList(req));
    }
}

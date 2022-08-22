package com.sales.app.controller;

import com.sales.app.domain.entity.FeedbackReport;
import com.sales.app.domain.request.FeedBackReq;
import com.sales.app.service.FeedbackService;
import com.sales.common.core.domain.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 投诉意见反馈控制层
 * @author: wuyingqiang
 * @date: 2022-08-10 15:44
 */

@Api(value = "投诉、意见反馈控制层")
@RestController
@RequestMapping("/feedBack")
public class FeedBackController {
    @Autowired
    private FeedbackService feedBackService;

    @GetMapping("/getComplaintList")
    @ApiOperation(value = "获取投诉工单列表")
    public BaseResult<List<FeedbackReport>> getComplaintList(FeedBackReq req){
        return BaseResult.ok(feedBackService.getComplaintList(req));
    }


}

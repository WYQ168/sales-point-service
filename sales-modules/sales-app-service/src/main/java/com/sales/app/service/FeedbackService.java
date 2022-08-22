package com.sales.app.service;

import com.sales.app.domain.entity.FeedbackReport;
import com.sales.app.domain.request.FeedBackReq;

import java.util.List;

public interface FeedbackService {

    /**
     * 获取投诉列表数据
     *
     * @param req 反馈投诉请求体
     * @return 投诉列表
     */
    List<FeedbackReport> getComplaintList(FeedBackReq req);

    /**
     * 发起投诉工单
     *
     * @param report 投诉实体
     * @return 发起投诉
     */
    Integer addComplaint(FeedbackReport report);
}

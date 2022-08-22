package com.sales.app.service.impl;

import com.sales.app.domain.entity.FeedbackReport;
import com.sales.app.domain.request.FeedBackReq;
import com.sales.app.mapper.FeedbackReportMapper;
import com.sales.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 投诉反馈业务层
 * @author: wuyingqiang
 * @date: 2022-08-10 16:53
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackReportMapper feedbackReportMapper;

    @Override
    public List<FeedbackReport> getComplaintList(FeedBackReq req) {
        return feedbackReportMapper.getComplaintList(req);
    }

    @Override
    public Integer addComplaint(FeedbackReport report) {
        report.setStatus(0);
        report.setCreateTime(new Date());
        return feedbackReportMapper.insert(report);
    }
}

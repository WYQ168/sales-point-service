package com.sales.app.mapper;

import com.sales.app.domain.entity.FeedbackReport;import com.sales.app.domain.request.FeedBackReq;import java.util.List;

public interface FeedbackReportMapper {
    int deleteByPrimaryKey(Long feedbackId);

    int insert(FeedbackReport record);

    int insertSelective(FeedbackReport record);

    FeedbackReport selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKeySelective(FeedbackReport record);

    int updateByPrimaryKey(FeedbackReport record);

    /**
     * 获取投诉列表数据
     *
     * @param req 反馈投诉请求体
     * @return 投诉列表
     */
    List<FeedbackReport> getComplaintList(FeedBackReq req);
}
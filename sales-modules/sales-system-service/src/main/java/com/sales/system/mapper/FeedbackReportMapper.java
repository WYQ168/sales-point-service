package com.sales.system.mapper;

import com.sales.system.domain.entity.FeedbackReport;

public interface FeedbackReportMapper {
    int deleteByPrimaryKey(Long feedbackId);

    int insert(FeedbackReport record);

    int insertSelective(FeedbackReport record);

    FeedbackReport selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKeySelective(FeedbackReport record);

    int updateByPrimaryKey(FeedbackReport record);
}
package com.sales.app.service;

import com.sales.app.domain.entity.IncomeRecord;
import com.sales.app.domain.request.BillReq;
import com.sales.app.domain.response.BillResp;
import com.sales.app.domain.request.IntegralReq;
import com.sales.app.domain.response.IntegralResp;

import java.util.List;

public interface IncomeRecordService {

    /**
     * 获取我的钱包中的账单记录
     * @param req 账单请求体
     * @return 账单记录
     */
    List<BillResp> getBillList(BillReq req);

    /**
     * 获取我的积分账户
     *
     * @param req 我的积分请求体
     * @return 积分信息
     */
    List<IntegralResp> getIntegralInfo(IntegralReq req);

    /**
     * 获取我的积分账户中的积分记录
     *
     * @param req 我的积分请求体
     * @return 积分记录
     */
    List<IncomeRecord> getIncomeRecordListByIntegral(IntegralReq req);
}

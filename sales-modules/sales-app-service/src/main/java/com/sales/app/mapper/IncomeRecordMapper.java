package com.sales.app.mapper;

import com.sales.app.domain.entity.IncomeRecord;import com.sales.app.domain.request.BillReq;
import com.sales.app.domain.request.IntegralReq;
import com.sales.app.domain.response.BillResp;import java.util.List;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Long incomeId);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Long incomeId);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);

    /**
     * 获取该用户下的所有金钱收益流水记录
     *
     * @param userId 用户id
     * @return 金钱收益流水记录
     */
    List<IncomeRecord> selectMoneyRecordsByUserId(Long userId);

    /**
     * 获取我的钱包中的账单记录
     *
     * @param req 账单请求体
     * @return 账单记录
     */
    List<BillResp> selectBillList(BillReq req);

    /**
     * 获取我的积分列表
     * @param req 我的积分请求体
     * @return 积分列表
     */
    List<IncomeRecord> selectIntegralList(IntegralReq req);

    /**
     * 获取我的积分账户中的积分记录
     *
     * @param req 我的积分请求体
     * @return 积分记录
     */
    List<IncomeRecord> selectRecordListByIntegral(IntegralReq req);
}
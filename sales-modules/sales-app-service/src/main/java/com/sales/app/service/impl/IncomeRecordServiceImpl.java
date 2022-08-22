package com.sales.app.service.impl;

import com.sales.app.constants.MachineTypeConstants;
import com.sales.app.domain.entity.IncomeRecord;
import com.sales.app.domain.request.BillReq;
import com.sales.app.domain.request.IntegralReq;
import com.sales.app.domain.response.BillResp;
import com.sales.app.domain.response.IntegralResp;
import com.sales.app.mapper.IncomeRecordMapper;
import com.sales.app.service.IncomeRecordService;
import com.sales.common.core.exception.base.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: wuyingqiang
 * @date: 2022-08-06 16:59
 */
@Service
public class IncomeRecordServiceImpl implements IncomeRecordService {

    @Autowired
    private IncomeRecordMapper incomeRecordMapper;

    @Override
    public List<BillResp> getBillList(BillReq req) {
        return incomeRecordMapper.selectBillList(req);
    }

    @Override
    public List<IntegralResp> getIntegralInfo(IntegralReq req) {
        Field[] fields = MachineTypeConstants.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                req.getIntegralSource().add(field.get(field.getName()).toString());
            } catch (IllegalAccessException e) {
                throw new BaseException("获取积分来源分类失败");
            }
        }
        // 将全部的积分收益记录进行划分
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectIntegralList(req);
        Map<String, List<IncomeRecord>> map = new LinkedHashMap<>();
        req.getIntegralSource().forEach(item -> {
            map.put(item, incomeRecords.stream().filter(incomeRecord ->
                    incomeRecord.getIncomeSource().equals(item)).collect(Collectors.toList()));
        });
        List<IntegralResp> respList = new ArrayList<>();
        for (String key : map.keySet()) {
            BigDecimal income = map.get(key).stream().filter(incomeRecord -> incomeRecord.getFlowingType() == 1)
                    .map(IncomeRecord::getIncomeAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal spending = map.get(key).stream().filter(incomeRecord -> incomeRecord.getFlowingType() == 1)
                    .map(IncomeRecord::getIncomeAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal total = income.subtract(spending);
            IntegralResp resp = new IntegralResp();
            resp.setIntegralAmount(total).setIntegralName(key);
            respList.add(resp);
        }

        return respList;
    }

    @Override
    public List<IncomeRecord> getIncomeRecordListByIntegral(IntegralReq req) {
        return incomeRecordMapper.selectRecordListByIntegral(req);
    }
}

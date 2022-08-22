package com.sales.system.mapper;

import com.sales.system.domain.entity.IncomeRecord;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Long incomeId);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Long incomeId);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);
}
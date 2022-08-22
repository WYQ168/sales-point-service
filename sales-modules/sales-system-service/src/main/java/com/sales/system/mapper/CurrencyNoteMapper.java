package com.sales.system.mapper;

import com.sales.system.domain.entity.CurrencyNote;

public interface CurrencyNoteMapper {
    int deleteByPrimaryKey(Long currencyId);

    int insert(CurrencyNote record);

    int insertSelective(CurrencyNote record);

    CurrencyNote selectByPrimaryKey(Long currencyId);

    int updateByPrimaryKeySelective(CurrencyNote record);

    int updateByPrimaryKey(CurrencyNote record);
}
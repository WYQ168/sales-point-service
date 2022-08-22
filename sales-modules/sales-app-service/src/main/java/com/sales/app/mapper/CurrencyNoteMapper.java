package com.sales.app.mapper;

import com.sales.app.domain.entity.CurrencyNote;

public interface CurrencyNoteMapper {
    int deleteByPrimaryKey(Long currencyId);

    int insert(CurrencyNote record);

    int insertSelective(CurrencyNote record);

    CurrencyNote selectByPrimaryKey(Long currencyId);

    int updateByPrimaryKeySelective(CurrencyNote record);

    int updateByPrimaryKey(CurrencyNote record);

    CurrencyNote selectByUserId(Long userId);
}
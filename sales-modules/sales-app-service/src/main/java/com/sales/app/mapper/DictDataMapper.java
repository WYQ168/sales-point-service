package com.sales.app.mapper;

import com.sales.app.domain.entity.DictData;

public interface DictDataMapper {
    int deleteByPrimaryKey(String dictCode);

    int insert(DictData record);

    int insertSelective(DictData record);

    DictData selectByPrimaryKey(String dictCode);

    int updateByPrimaryKeySelective(DictData record);

    int updateByPrimaryKey(DictData record);
}
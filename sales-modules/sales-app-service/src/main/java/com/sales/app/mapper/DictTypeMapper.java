package com.sales.app.mapper;

import com.sales.app.domain.entity.DictType;

public interface DictTypeMapper {
    int deleteByPrimaryKey(String dictId);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
}
package com.sales.system.mapper;

import com.sales.system.domain.entity.DictType;

import java.util.List;

public interface DictTypeMapper {
    int deleteByPrimaryKey(String dictId);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);

    List<DictType> selectAll();

}
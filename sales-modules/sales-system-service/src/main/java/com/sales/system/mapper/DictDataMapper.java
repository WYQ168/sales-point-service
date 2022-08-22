package com.sales.system.mapper;

import com.sales.system.domain.entity.DictData;
import com.sales.system.domain.entity.DictType;

import java.util.List;

public interface DictDataMapper {
    int deleteByPrimaryKey(String dictCode);

    int insert(DictData record);

    int insertSelective(DictData record);

    DictData selectByPrimaryKey(String dictCode);

    int updateByPrimaryKeySelective(DictData record);

    int updateByPrimaryKey(DictData record);

    /**
     * 通过数据类型查询字典数据列表
     *
     * @param dictType 数据类型
     * @return 数据列表
     */
    List<DictData> selectAllByType(String dictType);
}
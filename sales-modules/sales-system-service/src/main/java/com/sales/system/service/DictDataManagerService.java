package com.sales.system.service;

import com.sales.system.domain.entity.DictData;
import com.sales.system.domain.entity.DictType;

import java.util.List;

public interface DictDataManagerService {

    /**
     * 新增数据结构
     *
     * @param type 数据结构
     * @return 新增结果
     */
    Integer addDictType(DictType type);

    /**
     * 查询数据结构列表
     *
     * @return 数据结构列表
     */
    List<DictType> getDictTypeList();

    /**
     * 更新数据结构
     *
     * @param type 数据结构
     * @return 更新结果
     */
    Integer updateDictType(DictType type);

    /**
     * 删除数据结构
     *
     * @param typeId 结构id
     * @return 删除结果
     */
    Integer delDictType(String typeId);

    /**
     * 新增字典数据
     *
     * @param data 字典数据
     * @return 新增结果
     */
    Integer addDictData(DictData data);

    /**
     * 查询字典数据列表
     *
     * @return 字典数据列表
     */
    List<DictData> getDictDataList(String dictType);

    /**
     * 更新字典数据
     *
     * @param data 字典数据
     * @return 更新结果
     */
    Integer updateDictData(DictData data);

    /**
     * 删除字典数据
     *
     * @param dataId 数据id
     * @return 删除结果
     */
    Integer delDictData(String dataId);
}

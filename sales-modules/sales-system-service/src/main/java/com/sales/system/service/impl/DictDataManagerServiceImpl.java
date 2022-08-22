package com.sales.system.service.impl;

import com.sales.common.security.utils.SecurityUtils;
import com.sales.system.domain.entity.DictData;
import com.sales.system.domain.entity.DictType;
import com.sales.system.mapper.DictDataMapper;
import com.sales.system.mapper.DictTypeMapper;
import com.sales.system.service.DictDataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description: 数据字典管理业务层
 * @author: wuyingqiang
 * @date: 2022-08-22 10:04
 */

@Service
public class DictDataManagerServiceImpl implements DictDataManagerService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public Integer addDictType(DictType type) {
        type.setDictId(UUID.randomUUID().toString().replace("-",""));
        type.setCreateBy(SecurityUtils.getUserId());
        type.setCreateTime(new Date());
        return dictTypeMapper.insert(type);
    }

    @Override
    public List<DictType> getDictTypeList() {
        return dictTypeMapper.selectAll();
    }

    @Override
    public Integer updateDictType(DictType type) {
        type.setUpdateBy(SecurityUtils.getUserId());
        type.setUpdateTime(new Date());
        return dictTypeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public Integer delDictType(String typeId) {
        return dictTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public Integer addDictData(DictData data) {
        data.setDictCode(UUID.randomUUID().toString().replace("-",""));
        data.setCreateBy(SecurityUtils.getUserId());
        data.setCreateTime(new Date());
        return dictDataMapper.insert(data);
    }

    @Override
    public List<DictData> getDictDataList(String dictType) {
        return dictDataMapper.selectAllByType(dictType);
    }

    @Override
    public Integer updateDictData(DictData data) {
        data.setUpdateBy(SecurityUtils.getUserId());
        data.setUpdateTime(new Date());
        return dictDataMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public Integer delDictData(String dataId) {
        return dictDataMapper.deleteByPrimaryKey(dataId);
    }
}

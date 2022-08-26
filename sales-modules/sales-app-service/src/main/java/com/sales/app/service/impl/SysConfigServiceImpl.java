package com.sales.app.service.impl;

import java.util.List;

import com.sales.app.domain.entity.SysConfig;
import com.sales.app.mapper.SysConfigMapper;
import com.sales.app.service.ISysConfigService;
import com.sales.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统配置，以JSON格式记录常用配置，如阿里云，微信等 服务层实现
 *
 * @author zhangtailong
 * @date 2022-08-26
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     * 查询系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     *
     * @param id 系统配置，以JSON格式记录常用配置，如阿里云，微信等ID
     * @return 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     */
    @Override
    public SysConfig selectSysConfigById(Long id) {
        return sysConfigMapper.selectSysConfigById(id);
    }

    /**
     * 查询系统配置，以JSON格式记录常用配置，如阿里云，微信等列表
     *
     * @param sysConfig 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     * @return 系统配置，以JSON格式记录常用配置，如阿里云，微信等集合
     */
    @Override
    public List<SysConfig> selectSysConfigList(SysConfig sysConfig) {
        return sysConfigMapper.selectSysConfigList(sysConfig);
    }

    /**
     * 新增系统配置，以JSON格式记录常用配置，如阿里云，微信等
     *
     * @param sysConfig 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     * @return 结果
     */
    @Override
    public int insertSysConfig(SysConfig sysConfig) {
        return sysConfigMapper.insertSysConfig(sysConfig);
    }

    /**
     * 修改系统配置，以JSON格式记录常用配置，如阿里云，微信等
     *
     * @param sysConfig 系统配置，以JSON格式记录常用配置，如阿里云，微信等信息
     * @return 结果
     */
    @Override
    public int updateSysConfig(SysConfig sysConfig) {
        return sysConfigMapper.updateSysConfig(sysConfig);
    }

    /**
     * 删除系统配置，以JSON格式记录常用配置，如阿里云，微信等对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysConfigByIds(String ids) {
        return sysConfigMapper.deleteSysConfigByIds(Convert.toStrArray(ids));
    }

}

package com.sales.app.mapper;

import com.sales.app.domain.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long sysUserId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long sysUserId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
package com.sales.system.mapper;

import com.sales.system.domain.entity.AppUser;

public interface AppUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);
}
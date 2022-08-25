package com.sales.system.mapper;

import com.sales.system.domain.entity.MenuAuth;

public interface MenuAuthMapper {
    int deleteByPrimaryKey(Long authId);

    int insert(MenuAuth record);

    int insertSelective(MenuAuth record);

    MenuAuth selectByPrimaryKey(Long authId);

    int updateByPrimaryKeySelective(MenuAuth record);

    int updateByPrimaryKey(MenuAuth record);
}
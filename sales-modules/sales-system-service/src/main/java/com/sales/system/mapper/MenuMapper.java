package com.sales.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sales.system.domain.entity.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}
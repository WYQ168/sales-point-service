package com.sales.system.mapper;

import com.sales.system.domain.entity.Machine;

public interface MachineMapper {
    int deleteByPrimaryKey(Long machineId);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(Long machineId);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);
}
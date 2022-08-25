package com.sales.system.mapper;

import com.sales.system.domain.entity.Machine;
import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.request.MachineQueryReq;

import java.util.List;

public interface MachineMapper {
    int deleteByPrimaryKey(Long machineId);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(Long machineId);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);

    /**
     * 根据查询条件查询机具管理列表
     *
     * @param req 机具查询请求体
     * @return 机具列表
     */
    List<Machine> selectAllByCondition(MachineQueryReq req);
}
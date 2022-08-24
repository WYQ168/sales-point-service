package com.sales.app.mapper;

import com.sales.app.domain.entity.Machine;import com.sales.app.domain.request.BaseQueryReq;import com.sales.app.domain.request.MyMachineReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineMapper {
    int deleteByPrimaryKey(Long machineId);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(Long machineId);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);

    /**
     * 查询我的机具列表
     *
     * @param req 我的机具请求体
     * @return 我的机具
     */
    List<Machine> selectMyMachineList(MyMachineReq req);

    /**
     * 查询我的个人业绩中的机具数据
     *
     * @param req 基础查询类
     * @return 个人业绩的数据
     */
    List<Machine> selectPersonalPerformance(BaseQueryReq req);

    /**
     * 查询我的伙伴拥有的机具数据
     *
     * @param req 基础查询类
     * @return 我的伙伴拥有的机具列表
     */
    List<Machine> selectPartnerMachineList(BaseQueryReq req);
}
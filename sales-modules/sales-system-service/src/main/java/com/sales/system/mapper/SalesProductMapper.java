package com.sales.system.mapper;

import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.request.MachineQueryReq;

import java.util.List;

public interface SalesProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(SalesProduct record);

    int insertSelective(SalesProduct record);

    SalesProduct selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(SalesProduct record);

    int updateByPrimaryKey(SalesProduct record);

    /**
     * 根据条件查询机具产品列表
     *
     * @param req 机具产品更新请求体
     * @return 机具列表
     */
    List<SalesProduct> selectAllByCondition(MachineQueryReq req);
}
package com.sales.system.mapper;

import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.req.MachineQueryReq;

import java.util.List;

public interface SalesProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(SalesProduct record);

    int insertSelective(SalesProduct record);

    SalesProduct selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(SalesProduct record);

    int updateByPrimaryKey(SalesProduct record);

    /**
     * 更新机具产品对应状态
     *
     * @param req 机具产品更新请求体
     * @return 更新结果
     */
    List<SalesProduct> selectAllByCondition(MachineQueryReq req);
}
package com.sales.system.mapper;

import com.sales.system.domain.entity.AgentMerchant;

public interface AgentMerchantMapper {
    int deleteByPrimaryKey(Long agentId);

    int insert(AgentMerchant record);

    int insertSelective(AgentMerchant record);

    AgentMerchant selectByPrimaryKey(Long agentId);

    int updateByPrimaryKeySelective(AgentMerchant record);

    int updateByPrimaryKey(AgentMerchant record);
}
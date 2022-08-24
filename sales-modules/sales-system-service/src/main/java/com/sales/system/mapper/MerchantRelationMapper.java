package com.sales.system.mapper;

import com.sales.system.domain.entity.MerchantRelation;

public interface MerchantRelationMapper {
    int deleteByPrimaryKey(String relationId);

    int insert(MerchantRelation record);

    int insertSelective(MerchantRelation record);

    MerchantRelation selectByPrimaryKey(String relationId);

    int updateByPrimaryKeySelective(MerchantRelation record);

    int updateByPrimaryKey(MerchantRelation record);
}
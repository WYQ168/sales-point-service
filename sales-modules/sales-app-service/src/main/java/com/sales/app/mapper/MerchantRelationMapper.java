package com.sales.app.mapper;

import com.sales.app.domain.entity.MerchantRelation;

import java.util.List;

public interface MerchantRelationMapper {
    int deleteByPrimaryKey(String relationId);

    int insert(MerchantRelation record);

    int insertSelective(MerchantRelation record);

    MerchantRelation selectByPrimaryKey(String relationId);

    int updateByPrimaryKeySelective(MerchantRelation record);

    int updateByPrimaryKey(MerchantRelation record);

    /**
     * 通过用户id查询绑定的商户有多少个
     *
     * @param userId 用户id
     * @return 商户关系列表
     */
    List<MerchantRelation> selectRelationByUserId(Long userId);
}
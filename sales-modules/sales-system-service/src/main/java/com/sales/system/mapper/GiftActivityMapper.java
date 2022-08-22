package com.sales.system.mapper;

import com.sales.system.domain.entity.GiftActivity;

import java.util.List;

public interface GiftActivityMapper {
    int deleteByPrimaryKey(String giftId);

    int insert(GiftActivity record);

    int insertSelective(GiftActivity record);

    GiftActivity selectByPrimaryKey(String giftId);

    int updateByPrimaryKeySelective(GiftActivity record);

    int updateByPrimaryKey(GiftActivity record);

    /**
     * 查询所有的礼包活动列表
     *
     * @return 礼包列表
     */
    List<GiftActivity> selectAll();
}
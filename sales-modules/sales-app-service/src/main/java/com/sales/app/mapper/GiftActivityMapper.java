package com.sales.app.mapper;

import com.sales.app.domain.entity.GiftActivity;import com.sales.app.domain.response.GiftActivityResp;import java.util.List;

public interface GiftActivityMapper {
    int deleteByPrimaryKey(String giftId);

    int insert(GiftActivity record);

    int insertSelective(GiftActivity record);

    GiftActivity selectByPrimaryKey(String giftId);

    int updateByPrimaryKeySelective(GiftActivity record);

    int updateByPrimaryKey(GiftActivity record);

    /**
     * 获取礼包活动列表
     *
     * @param parentLabel 礼包分类标签
     * @return 礼包列表
     */
    List<GiftActivityResp> selectListByParentLabel(String parentLabel);
}
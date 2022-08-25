package com.sales.system.service;

import com.sales.system.domain.entity.GiftActivity;
import com.sales.system.domain.entity.Machine;
import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.request.MachineQueryReq;
import com.sales.system.domain.request.MachineUpdateReq;

import java.util.List;

public interface MachineManagerService {

    /**
     * 添加机具产品
     *
     * @param salesProduct 机具产品实体类
     * @return 新增结果
     */
    Integer addSalesProduct(SalesProduct salesProduct);

    /**
     * 更新机具产品对应状态
     *
     * @param req 机具产品更新请求体
     * @return 更新结果
     */
    Integer updateProductStatus(MachineUpdateReq req);

    /**
     * 查询机具列表
     *
     * @param req 机具查询实体
     * @return 机具列表
     */
    List<Machine> selectAllByCondition(MachineQueryReq req);

    /**
     * 添加礼包
     *
     * @param gift 礼包实体类
     * @return 新增结果
     */
    Integer addActivityGift(GiftActivity gift);

    /**
     * 查询所有的礼包活动列表
     *
     * @return 礼包列表
     */
    List<GiftActivity> getGiftList();
}

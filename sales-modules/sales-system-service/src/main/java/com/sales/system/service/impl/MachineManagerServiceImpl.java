package com.sales.system.service.impl;

import com.sales.common.security.utils.SecurityUtils;
import com.sales.system.domain.entity.GiftActivity;
import com.sales.system.domain.entity.Machine;
import com.sales.system.domain.entity.SalesProduct;
import com.sales.system.domain.request.MachineQueryReq;
import com.sales.system.domain.request.MachineUpdateReq;
import com.sales.system.mapper.GiftActivityMapper;
import com.sales.system.mapper.MachineMapper;
import com.sales.system.mapper.SalesProductMapper;
import com.sales.system.service.MachineManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 机具管理业务层
 * @author: wuyingqiang
 * @date: 2022-08-21 21:39
 */

@Service
public class MachineManagerServiceImpl implements MachineManagerService {

    @Autowired
    private SalesProductMapper salesProductMapper;

    @Autowired
    private GiftActivityMapper giftActivityMapper;

    @Autowired
    private MachineMapper machineMapper;

    @Override
    public Integer addSalesProduct(SalesProduct salesProduct) {
        salesProduct.setCreateBy(SecurityUtils.getUserId())
        .setCreateTime(new Date());
        return salesProductMapper.insert(salesProduct);
    }

    @Override
    public Integer updateProductStatus(MachineUpdateReq req) {
        SalesProduct salesProduct = salesProductMapper.selectByPrimaryKey(req.getProductId());
        if(salesProduct != null){
            salesProduct.setProductStatus(req.getStatus());
            salesProduct.setUpdateBy(SecurityUtils.getUserId());
            salesProduct.setUpdateTime(new Date());
        }
        return salesProductMapper.updateByPrimaryKeySelective(salesProduct);
    }

    @Override
    public List<Machine> selectAllByCondition(MachineQueryReq req) {
        return machineMapper.selectAllByCondition(req);
    }

    @Override
    public Integer addActivityGift(GiftActivity gift) {
        gift.setCreateBy(SecurityUtils.getUserId())
        .setUpdateTime(new Date());
        return giftActivityMapper.insert(gift);
    }

    @Override
    public List<GiftActivity> getGiftList() {
        return giftActivityMapper.selectAll();
    }
}

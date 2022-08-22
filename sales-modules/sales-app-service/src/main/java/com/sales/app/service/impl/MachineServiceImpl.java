package com.sales.app.service.impl;

import com.sales.app.constants.MembershipLevelConstants;
import com.sales.app.domain.entity.AppUser;
import com.sales.app.domain.entity.Machine;
import com.sales.app.domain.pojo.MerchantsData;
import com.sales.app.domain.pojo.TradingData;
import com.sales.app.domain.request.BaseQueryReq;
import com.sales.app.domain.request.MachineIssuedReq;
import com.sales.app.domain.request.MyMachineReq;
import com.sales.app.domain.response.PerformanceResp;
import com.sales.app.mapper.AppUserMapper;
import com.sales.app.mapper.MachineMapper;
import com.sales.app.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 机具业务层
 * @author: wuyingqiang
 * @date: 2022-08-10 17:11
 */

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Override
    public List<Machine> getMyMachineList(MyMachineReq req) {
        List<Machine> machines = machineMapper.selectMyMachineList(req);
        return machines;
    }

    @Override
    public Integer machineIssued(MachineIssuedReq req) {
        // 下发机具，绑定者成为了我的商户
        req.getProductList().stream().forEach(item->{
            item.setBindingBy(req.getIssuedUserId());
            item.setBindingTime(new Date());
            machineMapper.updateByPrimaryKeySelective(item);
        });

        return 1;
    }

    @Override
    public PerformanceResp getPersonalPerformance(BaseQueryReq req) {
        PerformanceResp resp = new PerformanceResp();
        List<Machine> list = machineMapper.selectPersonalPerformance(req);
        Map<String, List<Machine>> collect = list.stream().collect((Collectors.groupingBy(item -> item.getMachineType())));
        List<MerchantsData> merchantsData = new ArrayList<>();
        for(String key: collect.keySet()){
            MerchantsData data = new MerchantsData().setMerchantsType(key).setMerchantsNumber(collect.get(key).size());
            merchantsData.add(data);
        }
        resp.setNewMerchantsList(merchantsData);
        // TODO 交易数据统计
        TradingData tradingData = new TradingData();

        AppUser appUser = appUserMapper.selectByPrimaryKey(req.getUserId());
        List<AppUser> userList = appUserMapper.selectPartners(appUser.getRecommendCode());
        resp.setNewPartnerNumber(userList.size());
        return resp;
    }
}

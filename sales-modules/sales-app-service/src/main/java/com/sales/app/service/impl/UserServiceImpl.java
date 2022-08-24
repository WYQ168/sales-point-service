package com.sales.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sales.app.domain.entity.AppUser;
import com.sales.app.domain.entity.MerchantRelation;
import com.sales.app.domain.entity.Wallet;
import com.sales.app.domain.request.UserInfoReq;
import com.sales.app.domain.request.UpdateUserInfoReq;
import com.sales.app.enums.SystemEnum;
import com.sales.app.mapper.AppUserMapper;
import com.sales.app.mapper.MerchantRelationMapper;
import com.sales.app.mapper.WalletMapper;
import com.sales.app.service.UserService;
import com.sales.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @description: 用户业务层
 * @author: wuyingqiang
 * @date: 2022-08-05 16:11
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private MerchantRelationMapper merchantRelationMapper;

    @Override
    public AppUser getUserInfoById(Long userId) {
        return appUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<AppUser> getPartnersByCondition(UserInfoReq req) {
        AppUser user = appUserMapper.selectByPrimaryKey(req.getUserId());
        List<AppUser> appUsers = appUserMapper.selectListByInviteCode(user.getInviteCode());
        if(appUsers != null && appUsers.size()>0){
            if(!StringUtils.isEmpty(req.getPhone())){
                appUsers = appUsers.stream().filter(item->item.getPhoneMember().contains(req.getPhone())).collect(Collectors.toList());
            }
            if(!StringUtils.isEmpty(req.getUserName())){
                appUsers = appUsers.stream().filter(item->item.getUserName().contains(req.getUserName())).collect(Collectors.toList());
            }
        }
        return appUsers;
    }

    @Override
    public List<AppUser> getMerchantByCondition(UserInfoReq req) {
        // 通过商户关系表查询我的商户有多少
        List<MerchantRelation> merchantRelations = merchantRelationMapper.selectRelationByUserId(req.getUserId());

        List<Long> merchantIds = new ArrayList<>();
        if(merchantRelations != null && merchantRelations.size() > 0){
            merchantIds = merchantRelations.stream().map(MerchantRelation::getMerchantId).collect(Collectors.toList());
        }
        List<AppUser> users = appUserMapper.selectMyMerchantList(merchantIds);
        return users;
    }

    @Override
    public Boolean register(AppUser appUser) {
        Integer result = appUserMapper.insert(appUser);
        if(result != 1){
            return false;
        }
        // 用户注册成功需要初始化钱包信息
        Wallet wallet = new Wallet();
        wallet.setUserId(appUser.getUserId());
        wallet.setCreateTime(new Date());
        walletMapper.insert(wallet);

        return true;
    }

    @Override
    public Integer updatePhone(UpdateUserInfoReq req) {
        AppUser appUser = appUserMapper.selectByPrimaryKey(req.getUserId());
        // 核对用户身份证后再修改手机号
        if(req.getIdentityCard().equals(appUser.getIdentityMember())){
            appUser.setPhoneMember(req.getPhone());
            appUser.setUpdateTime(new Date());
            appUserMapper.updateByPrimaryKeySelective(appUser);
        }
        return null;
    }

    @Override
    public List<AppUser> getPartnerListByInviteCode(String inviteCode) {
        return appUserMapper.selectListByInviteCode(inviteCode);
    }

    @Override
    public JSONObject getUserInfo(String userName) {
        AppUser user = appUserMapper.selectByUserName(userName);
        if(user != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", UUID.randomUUID().toString());
            jsonObject.put("userid", user.getUserId());
            jsonObject.put("userType", SystemEnum.USER_TYPE_2.getValue());
            jsonObject.put("username", user.getUserName());
            jsonObject.put("loginTime", System.currentTimeMillis());
            jsonObject.put("user", user);
            return jsonObject;
        }
        return null;
    }

    @Override
    public Integer cancelUserPermission(Long userId) {
        AppUser appUser = appUserMapper.selectByPrimaryKey(userId);
        appUser.setDelFlag(1);
        return appUserMapper.updateByPrimaryKeySelective(appUser);
    }
}

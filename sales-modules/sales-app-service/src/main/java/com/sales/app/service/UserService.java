package com.sales.app.service;

import com.alibaba.fastjson.JSONObject;
import com.sales.app.domain.entity.AppUser;
import com.sales.app.domain.request.UserInfoReq;
import com.sales.app.domain.request.UpdateUserInfoReq;

import java.util.List;

public interface UserService {

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    AppUser getUserInfoById(Long userId);

    /**
     * 通过请求条件获取伙伴列表
     *
     * @param req 用户信息请求体
     * @return 伙伴信息列表
     */
    List<AppUser> getPartnersByCondition(UserInfoReq req);

    /**
     * 通过请求条件获取商户列表
     *
     * @param req 用户信息请求体
     * @return 商户信息列表
     */
    List<AppUser> getMerchantByCondition(UserInfoReq req);

    /**
     * 用户注册接口
     *
     * @param appUser 用户信息
     * @return 结果
     */
    Boolean register(AppUser appUser);

    /**
     * 修改用户信息
     *
     * @param req 用户信息修改类
     * @return 结果
     */
    Integer updatePhone(UpdateUserInfoReq req);

    /**
     * 通过邀请码获取我的合伙人
     *
     * @param inviteCode 邀请码
     * @return 获取我的合伙人列表
     */
    List<AppUser> getPartnerListByInviteCode(String inviteCode);

    /**
     * 通过用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    JSONObject getUserInfo(String userName);

    /**
     * 注销用户
     *
     * @param userId 用户id
     * @return 结果
     */
    Integer cancelUserPermission(Long userId);
}

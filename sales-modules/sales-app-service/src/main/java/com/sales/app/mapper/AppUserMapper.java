package com.sales.app.mapper;

import com.sales.app.domain.entity.AppUser;
import com.sales.app.domain.request.UserInfoReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);

    /**
     * 根据条件查询用户列表
     *
     * @param req 伙伴请求体
     * @return 我的伙伴列表
     */
    List<AppUser> selectPartnersByCondition(UserInfoReq req);

    /**
     * 根据邀请码获取我的伙伴列表
     *
     * @param recommendCode 邀请码
     * @return 我的伙伴用户列表
     */
    List<AppUser> selectPartners(String recommendCode);

    /**
     * 通过邀请码获取我的合伙人
     *
     * @param inviteCode 邀请码
     * @return 获取我的合伙人列表
     */
    List<AppUser> selectListByInviteCode(String inviteCode);

    /**
     * 通过用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    AppUser selectByUserName(@Param("userName") String userName);

    /**
     * 通过商户id列表查询商户信息
     *
     * @param merchantIds 商户id集合
     * @return 商户列表
     */
    List<AppUser> selectMyMerchantList(@Param("merchantIds") List<Long> merchantIds);
}
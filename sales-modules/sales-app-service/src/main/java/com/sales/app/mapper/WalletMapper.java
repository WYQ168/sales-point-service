package com.sales.app.mapper;

import com.sales.app.domain.entity.Wallet;

public interface WalletMapper {
    int deleteByPrimaryKey(Long walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Long walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

    /**
     * 通过用户id查询钱包信息
     *
     * @param userId 用户id
     * @return 我的钱包
     */
    Wallet selectByUserId(Long userId);
}
package com.sales.system.mapper;

import com.sales.system.domain.entity.Wallet;

public interface WalletMapper {
    int deleteByPrimaryKey(Long walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Long walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
}
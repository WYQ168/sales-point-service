package com.sales.app.service;

import com.sales.app.domain.response.MyWalletResp;

public interface WalletService {

    /**
     * 获取我的钱包信息
     * @param userId 用户id
     * @return 我的钱包信息
     */
    MyWalletResp getMyWalletInfo(Long userId);
}

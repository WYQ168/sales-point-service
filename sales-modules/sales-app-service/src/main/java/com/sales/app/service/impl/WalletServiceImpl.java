package com.sales.app.service.impl;

import com.sales.app.constants.IncomeSourceConstants;
import com.sales.app.domain.entity.IncomeRecord;
import com.sales.app.domain.entity.Wallet;
import com.sales.app.domain.response.MyWalletResp;
import com.sales.app.mapper.IncomeRecordMapper;
import com.sales.app.mapper.WalletMapper;
import com.sales.app.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 钱包业务层
 * @author: wuyingqiang
 * @date: 2022-08-06 9:07
 */
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private IncomeRecordMapper incomeRecordMapper;

    @Override
    public MyWalletResp getMyWalletInfo(Long userId) {
        MyWalletResp resp = new MyWalletResp();
        Wallet wallet = walletMapper.selectByUserId(userId);
        resp.setUserId(userId);
        resp.setTotalAvailableBalance(wallet.getAvailableBalance());
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectMoneyRecordsByUserId(userId);

        Map<String, List<IncomeRecord>> collect = incomeRecords.parallelStream().collect(Collectors.groupingBy(IncomeRecord::getIncomeSource));
        resp.setSettlementBalance(collect.get(IncomeSourceConstants.SETTLEMENT_BALANCE).stream()
                .map(IncomeRecord::getIncomeAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
        resp.setActivationBalance(collect.get(IncomeSourceConstants.ACTIVATION_BALANCE).stream()
                .map(IncomeRecord::getIncomeAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
        resp.setProsperousBalance(collect.get(IncomeSourceConstants.PROSPEROUS_BALANCE).stream()
                .map(IncomeRecord::getIncomeAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
        resp.setWpActivationBalance(collect.get(IncomeSourceConstants.WPACTIVATION_BALANCE).stream()
                .map(IncomeRecord::getIncomeAmount).reduce(BigDecimal.ZERO, BigDecimal::add));

        // TODO 可纳税金额计算规则

        return resp;
    }
}

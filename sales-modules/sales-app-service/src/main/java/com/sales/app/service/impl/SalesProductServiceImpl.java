package com.sales.app.service.impl;

import com.sales.app.constants.StewardLevelConstants;
import com.sales.app.domain.entity.*;
import com.sales.app.domain.request.ExchangeIntegralReq;
import com.sales.app.domain.request.GiftMachineReq;
import com.sales.app.domain.request.MachineByMallReq;
import com.sales.app.domain.request.MallProductReq;
import com.sales.app.domain.response.GiftActivityResp;
import com.sales.app.enums.OrderStatusEnum;
import com.sales.app.mapper.*;
import com.sales.app.service.SalesProductService;
import com.sales.common.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description: POS产品业务层
 * @author: wuyingqiang
 * @date: 2022-08-05 11:01
 */
@Service
public class SalesProductServiceImpl implements SalesProductService {

    @Autowired
    private SalesProductMapper salesProductMapper;

    @Autowired
    private GiftActivityMapper giftActivityMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AppUserMapper userMapper;

    @Autowired
    private CurrencyNoteMapper currencyNoteMapper;

    @Override
    public List<SalesProduct> getProductList(MallProductReq req) {
        return salesProductMapper.selectAllByCondition(req);
    }

    @Override
    public SalesProduct getProductInfo(Long productId) {
        return salesProductMapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<GiftActivityResp> getGiftActivityList(String parentLabel) {
        return giftActivityMapper.selectListByParentLabel(parentLabel);
    }

    @Override
    public List<SalesProduct> getIntegralProducts(String productLabel) {
        return salesProductMapper.selectIntegralProducts(productLabel);
    }

    @Override
    public Integer exchangeSalesProduct(ExchangeIntegralReq req) {
        Wallet wallet = walletMapper.selectByUserId(req.getUserId());
        if (req.getExchangeIntegral() > wallet.getGenericIntegral()) {
            throw new ServiceException("您的可用积分不足");
        }
        // TODO 积分减扣规则
        wallet.setGenericIntegral(wallet.getGenericIntegral() - req.getExchangeIntegral());

        Machine machine = new Machine();
        machine.setMachineAmount(req.getExchangeAmount())
                .setPurchaseBy(req.getUserId())
                .setPurchaseTime(new Date())
                .setMachineStatus(0)
                .setMachineType(req.getParentLabel());
        machineMapper.insert(machine);

        // 生成积分兑换的订单
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""))
                .setUserId(req.getUserId())
                .setOrderType("integral")
                .setOrderStatus(OrderStatusEnum.INTEGRAL_SUC.getState())
                .setCreateTime(new Date())
                .setCreateBy(req.getUserId());
        orderMapper.insert(order);

        return walletMapper.updateByPrimaryKeySelective(wallet);
    }

    @Override
    public Integer buyGiftMachine(GiftMachineReq req) {

        Wallet wallet = walletMapper.selectByUserId(req.getUserId());

        // 生成礼包购买的订单
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""))
                .setUserId(req.getUserId())
                .setTotalPrice(req.getGiftAmount())
                .setOrderType("gift")
                .setOrderStatus(OrderStatusEnum.PAY_SUC.getState())
                .setPayBankCard(req.getPayBankCard())
                .setCreateTime(new Date())
                .setCreateBy(req.getUserId())
                .setOrderAddressId(req.getAddressId());
        // TODO 银行卡支付逻辑
        if (false) {
            order.setOrderStatus(OrderStatusEnum.PAY_FAIL.getState());
        }
        orderMapper.insert(order);

        Machine machine = new Machine();
        machine.setMachineAmount(req.getGiftQuantity())
                .setPurchaseBy(req.getUserId())
                .setPurchaseTime(new Date())
                .setMachineStatus(0)
                .setMachineType(req.getParentLabel());

        return machineMapper.insert(machine);
    }

    @Override
    public List<Order> getOrderListByGift(Long userId) {
        List<Order> list = orderMapper.selectOrderListByGift(userId);
        return list;
    }

    @Override
    public List<Order> getUnpaidGiftOrderList(Long userId) {
        return orderMapper.selectUnpaidGiftOrderList(userId);
    }

    @Override
    public String buyMachineByMall(MachineByMallReq req) {
        AppUser appUser = userMapper.selectByPrimaryKey(req.getUserId());
        if (req.getMachineAmount() < 1000) {
            appUser.setStewardLevel(StewardLevelConstants.ORDINARY);

        } else if (req.getMachineAmount() <= 1999) {
            appUser.setStewardLevel(StewardLevelConstants.SILVER);

        } else if (req.getMachineAmount() >= 2000) {
            appUser.setStewardLevel(StewardLevelConstants.GOLD);

        }
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""))
                .setUserId(req.getUserId())
                .setTotalPrice(req.getTotalPrice())
                .setOrderType("mall")
                .setOrderStatus(OrderStatusEnum.PAY_SUC.getState())
                .setCreateTime(new Date())
                .setCreateBy(req.getUserId())
                .setOrderAddressId(req.getAddressId());
        // 可以使用流通券抵扣和现金方式购买
        if ("currencyNote".equals(req.getBuyType())) {
            CurrencyNote currencyNote = currencyNoteMapper.selectByUserId(req.getUserId());
            if (currencyNote.getCurrencyNoteNumber() > req.getNoteAmount()) {
                currencyNote.setCurrencyNoteNumber(currencyNote.getCurrencyNoteNumber()- req.getNoteAmount());
                currencyNoteMapper.updateByPrimaryKeySelective(currencyNote);
            }
        } else if ("cash".equals(req.getBuyType())) {

            // TODO 银行卡支付逻辑
            if (false) {
                order.setOrderStatus(OrderStatusEnum.PAY_FAIL.getState());
            }
        }

        orderMapper.insert(order);
        userMapper.updateByPrimaryKeySelective(appUser);
        return null;
    }
}

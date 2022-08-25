package com.sales.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.sales.app.constants.StewardLevelConstants;
import com.sales.app.domain.entity.*;
import com.sales.app.domain.request.ExchangeIntegralReq;
import com.sales.app.domain.request.GiftMachineReq;
import com.sales.app.domain.request.MachineByMallReq;
import com.sales.app.domain.request.MallProductReq;
import com.sales.app.domain.response.GiftActivityResp;
import com.sales.app.enums.MachineStatusEnum;
import com.sales.app.enums.OrderStatusEnum;
import com.sales.app.enums.OrderTypeEnum;
import com.sales.app.mapper.*;
import com.sales.app.service.SalesProductService;
import com.sales.app.utils.MathUtil;
import com.sales.common.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

        String simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 生成积分兑换的订单
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""))
                .setUserId(req.getUserId())
                .setOrderNo(OrderTypeEnum.INTEGRAL.getOrderCode() + simpleDateFormat + MathUtil.getRandomNumString(5))
                .setOrderType(OrderTypeEnum.INTEGRAL.getOrderType())
                .setOrderStatus(OrderStatusEnum.EXCHANGE_AUDIT.getState())
                .setCreateTime(new Date())
                .setCreateBy(req.getUserId());
        orderMapper.insert(order);

        return walletMapper.updateByPrimaryKeySelective(wallet);
    }

    @Override
    public Integer buyGiftMachine(GiftMachineReq req) {
        Wallet wallet = walletMapper.selectByUserId(req.getUserId());

        String simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodName", req.getGiftName());
        jsonObject.put("goodPrice", req.getGiftAmount());
        jsonObject.put("goodNumber", req.getGiftQuantity());
        // 生成礼包购买的订单
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""))
                .setUserId(req.getUserId())
                .setTotalPrice(req.getGiftAmount())
                .setGoodInfo(jsonObject.toJSONString())
                .setOrderNo(OrderTypeEnum.GIFT.getOrderCode() + simpleDateFormat + MathUtil.getRandomNumString(5))
                .setOrderType(OrderTypeEnum.GIFT.getOrderType())
                .setOrderStatus(OrderStatusEnum.NEW_ORDER.getState())
                .setGoodInfo(jsonObject.toJSONString())
                .setPayBankCard(req.getPayBankCard())
                .setCreateTime(new Date())
                .setCreateBy(req.getUserId())
                .setOrderAddressId(req.getAddressId());
        orderMapper.insert(order);

        Machine machine = new Machine();
        machine.setMachineAmount(req.getGiftQuantity())
                .setPurchaseBy(req.getUserId())
                .setPurchaseTime(new Date())
                .setMachineStatus(MachineStatusEnum.NO_ACTIVATION.getCode())
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
    public String buyMachineByMall(MachineByMallReq req) throws AlipayApiException {
        AppUser appUser = userMapper.selectByPrimaryKey(req.getUserId());
        if (req.getMachineAmount() < 1000) {
            appUser.setStewardLevel(StewardLevelConstants.ORDINARY);

        } else if (req.getMachineAmount() <= 1999) {
            appUser.setStewardLevel(StewardLevelConstants.SILVER);

        } else if (req.getMachineAmount() >= 2000) {
            appUser.setStewardLevel(StewardLevelConstants.GOLD);

        }

        String simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodName", req.getGoodName());
        jsonObject.put("goodPrice", req.getTotalPrice());
        jsonObject.put("goodNumber", req.getMachineAmount());
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""))
                .setUserId(req.getUserId())
                .setTotalPrice(req.getTotalPrice())
                .setGoodInfo(jsonObject.toJSONString())
                .setOrderNo(OrderTypeEnum.MALL.getOrderCode() + simpleDateFormat + MathUtil.getRandomNumString(5))
                .setOrderType(OrderTypeEnum.MALL.getOrderType())
                .setOrderStatus(OrderStatusEnum.NEW_ORDER.getState())
                .setCreateTime(new Date())
                .setCreateBy(req.getUserId())
                .setOrderAddressId(req.getAddressId());

        orderMapper.insert(order);
        // 使用流通券抵扣方式购买
        if ("currencyNote".equals(req.getBuyType())) {
            CurrencyNote currencyNote = currencyNoteMapper.selectByUserId(req.getUserId());
            if (currencyNote.getCurrencyNoteNumber() > req.getNoteAmount()) {
                // 流通券方式支付
                order.setPayType(4);
                currencyNote.setCurrencyNoteNumber(currencyNote.getCurrencyNoteNumber() - req.getNoteAmount());
                currencyNoteMapper.updateByPrimaryKeySelective(currencyNote);
            }
        } // 使用支付宝支付购买
        else if ("cash".equals(req.getBuyType())) {
            // 现金支付
            order.setPayType(1);
        }
        orderMapper.updateByPrimaryKeySelective(order);
        userMapper.updateByPrimaryKeySelective(appUser);
        return order.getOrderId();
    }
}

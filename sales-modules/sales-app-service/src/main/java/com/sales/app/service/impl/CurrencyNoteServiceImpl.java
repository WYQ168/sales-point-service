package com.sales.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sales.app.domain.entity.CurrencyNote;
import com.sales.app.domain.entity.Order;
import com.sales.app.domain.entity.SalesProduct;
import com.sales.app.domain.entity.Wallet;
import com.sales.app.domain.request.CashPosBuyReq;
import com.sales.app.domain.request.CurrencyBuyReq;
import com.sales.app.domain.response.CurrencyNoteResp;
import com.sales.app.enums.OrderStatusEnum;
import com.sales.app.enums.OrderTypeEnum;
import com.sales.app.mapper.CurrencyNoteMapper;
import com.sales.app.mapper.OrderMapper;
import com.sales.app.mapper.SalesProductMapper;
import com.sales.app.mapper.WalletMapper;
import com.sales.app.service.CurrencyNoteService;
import com.sales.app.utils.MathUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @description: 流通券账户业务层
 * @author: wuyingqiang
 * @date: 2022-08-08 10:05
 */

@Service
public class CurrencyNoteServiceImpl implements CurrencyNoteService {

    @Autowired
    private CurrencyNoteMapper currencyNoteMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private SalesProductMapper salesProductMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public CurrencyNoteResp getCurrencyInfo(Long userId) {
        CurrencyNoteResp resp = new CurrencyNoteResp();
        CurrencyNote currencyNote = currencyNoteMapper.selectByUserId(userId);
        if(currencyNote != null){
            BeanUtils.copyProperties(currencyNote,resp);
        }
        Wallet wallet = walletMapper.selectByUserId(userId);
        if(wallet != null){
            resp.setIntegral(wallet.getGenericIntegral());
        }
        return resp;
    }

    @Override
    public Integer buyByFull(CurrencyBuyReq req) {
        CurrencyNote currencyNote = currencyNoteMapper.selectByUserId(req.getUserId());
        // TODO 银联支付扣除金额，更新对应数据

        if(true){
            currencyNote.getBalanceAmount().subtract(req.getTotalMoney());
            currencyNote.setCurrencyNoteNumber(currencyNote.getCurrencyNoteNumber()+ req.getBuyAmount());
            currencyNote.setUpdateTime(new Date());

        }

        return currencyNoteMapper.updateByPrimaryKeySelective(currencyNote);
    }

    @Override
    public Integer cashIntegralBuy(CurrencyBuyReq req) {
        CurrencyNote currencyNote = currencyNoteMapper.selectByUserId(req.getUserId());
        // TODO 银联支付扣除金额，更新对应数据

        if(true){
            currencyNote.getBalanceAmount().subtract(req.getTotalMoney());
            currencyNote.setCurrencyNoteNumber(currencyNote.getCurrencyNoteNumber()+ req.getBuyAmount());
            currencyNote.setUpdateTime(new Date());
        }

        Wallet wallet = walletMapper.selectByUserId(req.getUserId());
        if(wallet != null){
            wallet.setGenericIntegral(wallet.getGenericIntegral() - req.getIntegralUsed());
            walletMapper.updateByPrimaryKeySelective(wallet);
        }

        return currencyNoteMapper.updateByPrimaryKeySelective(currencyNote);
    }

    @Override
    public Integer cashAndPosBuy(CashPosBuyReq req) {
        CurrencyNote currencyNote = currencyNoteMapper.selectByUserId(req.getUserId());
        SalesProduct salesProduct = salesProductMapper.selectByProductSn(req.getMachineNumber());
        int orderStatus = OrderStatusEnum.NEW_ORDER.getState();

        if(true){
            currencyNote.getBalanceAmount().subtract(req.getTotalMoney());
            currencyNote.setCurrencyNoteNumber(currencyNote.getCurrencyNoteNumber()+ req.getBuyAmount());
            currencyNote.setUpdateTime(new Date());
            orderStatus = OrderStatusEnum.PAY_SUC.getState();
        }

        // 生成订单逻辑
        Order order = new Order();
        String simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodName", req.getMachineName());
        jsonObject.put("goodPrice", req.getTotalMoney());
        jsonObject.put("goodNumber", req.getBuyAmount());
        order.setOrderId(UUID.randomUUID().toString().replace("-",""))
                .setUserId(req.getUserId())
                .setCourierCompany(req.getTrackingCompany())
                .setGoodInfo(jsonObject.toJSONString())
                .setOrderNo(OrderTypeEnum.MALL.getOrderCode() + simpleDateFormat + MathUtil.getRandomNumString(5))
                .setCourierCompany(req.getTrackingNumber())
                .setOrderStatus(orderStatus)
                .setCreateBy(req.getUserId())
                .setGoodInfo(JSONObject.toJSONString(salesProduct))
                .setCreateTime(new Date());
        orderMapper.insert(order);

        return currencyNoteMapper.updateByPrimaryKeySelective(currencyNote);
    }
}

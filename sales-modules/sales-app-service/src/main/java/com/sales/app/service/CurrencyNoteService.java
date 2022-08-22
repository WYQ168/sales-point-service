package com.sales.app.service;

import com.sales.app.domain.entity.CurrencyNote;
import com.sales.app.domain.request.CashPosBuyReq;
import com.sales.app.domain.request.CurrencyBuyReq;
import com.sales.app.domain.response.CurrencyNoteResp;

public interface CurrencyNoteService {

    /**
     * 获取流通券账户信息
     *
     * @param userId 用户id
     * @return 流通券
     */
    CurrencyNoteResp getCurrencyInfo(Long userId);

    /**
     * 全款购买流通券
     *
     * @param req 流通券购买请求体
     * @return 结果
     */
    Integer buyByFull(CurrencyBuyReq req);

    /**
     * 现金+积分购买流通券
     *
     * @param req 流通券购买请求体
     * @return 结果
     */
    Integer cashIntegralBuy(CurrencyBuyReq req);

    /**
     * 使用mpos+现金购买流通券
     *
     * @param req mpos+现金购买请求类
     * @return 结果
     */
    Integer cashAndPosBuy(CashPosBuyReq req);
}

package com.sales.app.service;

import com.sales.app.domain.request.TradeInfoReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UnionpayService {
    /**
     *
     * @param req
     * @param resp
     * @param tradeInfo
     * @throws IOException
     */
    void openAndConsume(HttpServletRequest req, HttpServletResponse resp, TradeInfoReq tradeInfo) throws IOException;

}

package com.sales.app.service.impl;

import com.sales.app.domain.request.IntegralOrderReq;
import com.sales.app.domain.request.TradeInfoReq;
import com.sales.app.domain.response.IntegralOrderResp;
import com.sales.app.mapper.OrderMapper;
import com.sales.app.service.OrderService;
import com.sales.app.utils.unionpay.demo.DemoBase;
import com.sales.app.utils.unionpay.sdk.AcpService;
import com.sales.app.utils.unionpay.sdk.SDKConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 订单业务层
 * @author: wuyingqiang
 * @date: 2022-08-15 9:49
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<IntegralOrderResp> getIntegralOrderList(IntegralOrderReq req) {
        return orderMapper.selectIntegralOrderList(req);
    }

}

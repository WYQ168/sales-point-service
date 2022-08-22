package com.sales.im.service;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.sales.im.yunxin.bean.callback.ReqBody;
import com.sales.im.yunxin.bean.callback.RespBody;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/8/2 23:43
 */
public interface IYunXinImEventService {
    void imMsgProcess(Message message, Channel channel, JSONObject msg) throws IOException;
    Integer imMsgToMq(JSONObject msg);
    RespBody msgCallbackListen(ReqBody reqData);
}

package com.sales.im.Listener;

import com.alibaba.fastjson.JSONObject;
import com.sales.im.constant.ImMqConstants;
import com.sales.im.domain.entry.Sms;
import com.sales.im.utils.SampleSendSmsUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 辉哥
 * @DateTime: 2020/11/9 下午5:32
 * @Description: TODO
 */
@Component
@Log4j2
public class ReceiveSmsHandler {

    @Autowired
    private SampleSendSmsUtil sampleSendSmsUtil;

    @RabbitListener(queues = {ImMqConstants.QUEUE_INFORM_SMS})
    public void accept_sms(byte[] msg, Message message) {
        try {
            Sms sms = JSONObject.parseObject(msg,Sms.class);
            sampleSendSmsUtil.send(sms);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }
}

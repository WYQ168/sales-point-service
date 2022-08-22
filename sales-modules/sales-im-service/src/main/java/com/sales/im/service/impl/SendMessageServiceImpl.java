package com.sales.im.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sales.common.core.constant.Constants;
import com.sales.common.core.domain.BaseResult;
import com.sales.common.core.exception.ServiceException;
import com.sales.common.redis.service.RedisService;
import com.sales.im.constant.ImMqConstants;
import com.sales.im.domain.entry.Sms;
import com.sales.im.service.SendMessageService;
import com.sales.im.utils.ShortUrl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 16554
 * @create 2022/7/22 16:27
 * @apiNote
 */
@Service
public class SendMessageServiceImpl implements SendMessageService {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	RedisService redisService;

	private static final String SMS_ROUTINGKEY = "inform.sms";

	// rabbitmq 消息过期时间,单位：毫秒,这里3分钟
	private static final String EXPIRATION = "300000";

	@Override
	public BaseResult sendSMSCode(Sms sms) {
		// 生成6位验证码
		long codeL = System.nanoTime();
		String codeStr = Long.toString(codeL);
		String substring = codeStr.substring(codeStr.length() - 8, codeStr.length() - 2);
		sms.setCode(substring);
		Map<String, Object> voMap = new HashMap<>();
		try {
			String uuid = UUID.randomUUID().toString();
			// 存储到redis
			String verifyKey = Constants.SMS_CODE_KEY + uuid;
			redisService.setCacheObject(verifyKey, substring, Constants.SMS_EXPIRATION, TimeUnit.MINUTES);
			voMap.put("phone", sms.getPhone());
			voMap.put("uuid", uuid);
			// 发送消息
			// 设置消息唯一表示
			// 发布消息的时候使用，存储在消息的headers中
			CorrelationData correlationData = new CorrelationData(uuid);
			MessageProperties messageProperties = new MessageProperties();
			// 设置过期时间，
			messageProperties.setExpiration(EXPIRATION);
			Message message = new Message(JSONObject.toJSONBytes(sms), messageProperties);
			rabbitTemplate.convertAndSend(ImMqConstants.EXCHANGE_TOPIC_INFORM, SMS_ROUTINGKEY, message, correlationData);
		} catch (Exception e) {
			throw new ServiceException("发送失败！请稍后再试！");
		}
		return BaseResult.ok(voMap);
	}

	/**
	 * 发送通知
	 * @param sms
	 * @return
	 */
	@Override
	public BaseResult sendNotice(Sms sms) {
		// 生成短连接
		sms.setUrl(ShortUrl.transShortUrl(sms.getUrl()));
		Map<String, Object> voMap = new HashMap<>();
		try {
			String uuid = UUID.randomUUID().toString();
			// 发送消息
			// 设置消息唯一表示
			// 发布消息的时候使用，存储在消息的headers中
			CorrelationData correlationData = new CorrelationData(uuid);
			Message message = new Message(JSONObject.toJSONBytes(sms));
			rabbitTemplate.convertAndSend(ImMqConstants.EXCHANGE_TOPIC_INFORM, SMS_ROUTINGKEY, message, correlationData);
		} catch (Exception e) {
			throw new ServiceException("发送失败！请稍后再试！");
		}
		return BaseResult.ok(voMap);
	}

}

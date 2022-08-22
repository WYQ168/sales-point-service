package com.sales.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.sales.im.constant.ImMqConstants;
import com.sales.im.enums.BanYuRespCodeEnum;
import com.sales.im.service.IYunXinImEventService;
import com.sales.im.yunxin.bean.callback.ReqBody;
import com.sales.im.yunxin.bean.callback.RespBody;
import com.sales.im.yunxin.bean.msgbody.TextMsg;
import com.sales.im.yunxin.enums.EventTypeEnum;
import com.sales.im.yunxin.enums.MsgTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/8/2 23:48
 */
@Slf4j
@Service
public class YunXinImEventServiceImpl implements IYunXinImEventService {
	@Autowired
	RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = ImMqConstants.QUEUE)
	@RabbitHandler
	@Override
	public void imMsgProcess(Message message, Channel channel, JSONObject msg) throws IOException {
		// 消息处理成功，false表示每次仅接受一个请求
		int pocessResult = 0;

		// 对接收益计算，等级计算，消息入库等
		try {
			// TO DO LIST
			Thread.sleep(1000);
			pocessResult = 1;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			if (pocessResult > 0) {
				// 消息处理成功，确认消息，false表示每次仅接受一个请求
				log.info("消息处理成功:" + msg.toJSONString());
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			} else {
				// 消息处理失败，拒绝消息，回滚给其他消费者
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer imMsgToMq(JSONObject msg) {
		log.info("消息抄送接收成功:" + msg);
		rabbitTemplate.convertAndSend(ImMqConstants.EXCHANGE, ImMqConstants.BINDING, msg);
		return 1;

	}

	@Override
	public RespBody msgCallbackListen(ReqBody reqData) {
		log.info("消息回调成功:" + reqData);
		RespBody respBody = new RespBody();
		// 内容检测
		int checkResult = 20000;
		checkResult = checkSensitiveWord(reqData);
		if (checkResult != 0) {
			respBody.setErrCode(1);
			respBody.setResponseCode(checkResult);
			return respBody;
		}
		// 用户计费处理
		checkResult = userBillProcess(reqData);
		if (checkResult != 0) {
			respBody.setErrCode(1);
			respBody.setResponseCode(checkResult);
			return respBody;
		}
		respBody.setErrCode(0);
		return respBody;

	}

	/**
	 * @param reqData:
	 * @return com.sales.im.yunxin.bean.callback.RespBody
	 * @desc: 用户计费逻辑检测
	 * @author wuyingqiang
	 * @date 2022/7/22 20:42
	 */
	private int userBillProcess(ReqBody reqData) {
		// 单聊消息类型
		if (EventTypeEnum.P2P_MSG.getValue().intValue() == reqData.getEventType().intValue()) {
			// 查询发送用户性别
			// 查询两者关注关系
			// 查询用户余额
			// 计费扣除逻辑
		}
		// 音视频回调
		if (EventTypeEnum.VV_CALL.getValue().intValue() == reqData.getEventType().intValue()) {

		}
		return 0;
	}

	/**
	 * @param reqData:
	 * @return java.lang.Integer
	 * @desc: 敏感字检测
	 * @author wuyingqiang
	 * @date 2022/7/22 21:12
	 */
	private Integer checkSensitiveWord(ReqBody reqData) {
		if (MsgTypeEnum.TEXT.getValue().equals(reqData.getMsgType())) {
			TextMsg textMsg = JSON.parseObject(reqData.getBody(), TextMsg.class);
			// 检测敏感字
			if (textMsg.getMsg().contains("微信")) {
				return BanYuRespCodeEnum.SENSITIVE_WORD.getCode();
			}
		}
		return 0;
	}
}

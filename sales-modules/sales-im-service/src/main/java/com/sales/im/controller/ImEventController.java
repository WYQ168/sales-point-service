package com.sales.im.controller;

import com.alibaba.fastjson.JSONObject;
import com.sales.common.core.domain.BaseResult;
import com.sales.common.redis.service.RedisService;
import com.sales.im.domain.entry.Sms;
import com.sales.im.service.IYunXinImEventService;
import com.sales.im.service.SendMessageService;
import com.sales.im.yunxin.bean.callback.ReqBody;
import com.sales.im.yunxin.bean.callback.RespBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/yunxin")
public class ImEventController {
	@Autowired
	IYunXinImEventService imEventService;
	@Autowired
	RedisService redisService;
	@Autowired
	SendMessageService sendMessageService;

	/**
	 * 消息抄送
	 */
	@PostMapping("/imMsgReport")
	public BaseResult<Integer> imMsgReport(@RequestBody JSONObject msg) {
		return BaseResult.ok(imEventService.imMsgToMq(msg));
	}

	/**
	 * 消息回调
	 */
	@PostMapping("/imMsgReceive")
	public RespBody imMsgReceive(@RequestBody ReqBody reqBody) {
		return imEventService.msgCallbackListen(reqBody);
	}

	/**
	 * 获取短信验证码
	 *
	 * @return
	 */
	@PostMapping("/getSmsCode")
	public BaseResult<?> getSmsCode(@RequestBody @Valid Sms sms) {
		return sendMessageService.sendSMSCode(sms);
	}

	@PostMapping("/getNoticeCode")
	public BaseResult<?> getNoticeCode(@RequestBody @Valid Sms sms) {
		return sendMessageService.sendNotice(sms);
	}


}
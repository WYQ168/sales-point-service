package com.sales.im.service;

import com.sales.common.core.domain.BaseResult;
import com.sales.im.domain.entry.Sms;

/**
 * @Author 16554
 * @create 2022/7/22 16:25
 * @apiNote
 */
public interface SendMessageService {
	public BaseResult sendSMSCode(Sms sms);

	public BaseResult sendNotice(Sms sms);
}

package com.sales.im.yunxin.bean.callback;

import lombok.Data;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/8/3 18:43
 */
@Data
public class ReqBody {
    private String body;
    private Integer eventType;
    private String fromClientType;
    private String fromAccount;
    private String fromDeviceId;
    private String fromNick;
    private String msgTimestamp;
    private String msgType;
    private String msgidClient;
    private String to;
    private String fromClientIp;
    private String fromClientPort;
}

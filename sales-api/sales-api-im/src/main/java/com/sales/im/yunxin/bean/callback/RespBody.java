package com.sales.im.yunxin.bean.callback;

import lombok.Data;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/8/3 18:49
 */
@Data
public class RespBody {
    /**
     * 0：表示回调通过，允许执行。
     * 1：表示回调不通过，取消执行。如果设置了合法的自定义错误码（responseCode），则发送端会收到自定义错误码，否则发送端会收到403错误码。
     */
    private Integer errCode;
    /**
     * 1、当errCode=1时有效
     * 2、范围是20000-20099，其他值无效，会被忽略
     * 3、特别的，对于消息类型的第三方回调（eventType=1、2、6、22），支持设置为200的错误码，客户端表现为消息发送成功，其实消息发送失败
     */
    private Integer responseCode;
    /**
     * 1、对于消息类型的第三方回调有效（eventType=1、2、6、22），用于篡改消息内容
     * 2、JSON格式，支持body、attach、ext三个字段（均可选，若不填则不替换），三个字段的长度限制和正常发消息的限制一样。例子：{"body":"xxx","attach":"xxx","ext":"123"}。
     * 3、效果：消息接收方收到的消息的上述三个字段将会被替换，消息发送方无感知，但是消息发送方的多端设备收到的消息是修改后的，此外，离线消息、漫游消息、云端历史消息，存储的均是修改后的消息内容，因此不管是消息发送方还是接收方，从云信服务器获取到的消息均是修改后的消息。
     */
    private String modifyResponse;
    /**
     * 1、对于消息类型的第三方回调有效（eventType=1、2、6、22），用于传递第三方回调的扩展信息，最大1024个字符
     * 2、消息发送者和消息接收者均能获取该扩展字段（需要SDK版本大于等于v7.7.0）
     * 3、若errCode=1，则只有消息发送者能获取到该字段
     */
    private String callbackExt;
}

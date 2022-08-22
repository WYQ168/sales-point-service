package com.sales.rabbitmq.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc: 消息响应结果
 * @author wuyingqiang
 * @date 2022/8/2 13:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailResponse {

    private boolean isSuccess;

    private Integer code;

    private String msg;
}

package com.sales.rabbitmq.cousumer;


import com.sales.rabbitmq.common.DetailResponse;

/**
 * @desc: TODO 消息消费者
 * @author wuyingqiang
 * @date 2022/8/2 13:01
 */
public interface MessageConsumer {
    DetailResponse consume();
}

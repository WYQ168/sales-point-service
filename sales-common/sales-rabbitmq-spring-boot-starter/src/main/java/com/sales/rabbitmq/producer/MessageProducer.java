package com.sales.rabbitmq.producer;

import com.sales.rabbitmq.common.DetailResponse;

public interface MessageProducer {


    DetailResponse send(Object message);

    DetailResponse send(MessageWithTime messageWithTime);
}
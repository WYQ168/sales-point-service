package com.sales.rabbitmq.cousumer;
import com.sales.rabbitmq.common.DetailResponse;

public interface MessageProcess<T> {
    DetailResponse process(T message);
}

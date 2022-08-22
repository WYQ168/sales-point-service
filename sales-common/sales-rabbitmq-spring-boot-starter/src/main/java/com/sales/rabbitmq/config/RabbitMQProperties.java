package com.sales.rabbitmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @desc: 队列配置
 * @author wuyingqiang
 * @date 2022/8/2 13:40
 */
@Data
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQProperties {

    private boolean enable;
    private String host;
    private int port;
    private String username;

    private String password;

    private String virtualHost;

    private boolean publisherConfirms = true;
    private boolean publisherReturns = true;
}

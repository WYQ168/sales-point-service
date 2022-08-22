package com.sales.rabbitmq.cousumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.ShutdownSignalException;
import com.sales.rabbitmq.common.DetailResponse;
import com.sales.rabbitmq.common.MqConstants;
import com.sales.rabbitmq.enums.ConsumerResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @desc: TODO
 * @author wuyingqiang
 * @date 2022/8/2 13:00
 */
@Slf4j
@SuppressWarnings("all")
public class FastBuildRabbitMqConsumer {

    private ConnectionFactory connectionFactory;
    
    public FastBuildRabbitMqConsumer(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }




    public <T> MessageConsumer buildMessageConsumer(String exchange, String routingKey, final String queue,
                                                                               final MessageProcess<T> messageProcess, String type) throws IOException {
        final Connection connection = connectionFactory.createConnection();

        //1 创建连接和channel
        buildQueue(exchange, routingKey, queue, connection, type);

        //2 设置message序列化方法
        final MessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
        final MessageConverter messageConverter = new Jackson2JsonMessageConverter();

        //3 consume
        return new MessageConsumer() {
            Channel channel;
            {channel = connection.createChannel(false);}

           
			@Override
            public DetailResponse consume() {
                try {
                    //1 通过basicGet获取原始数据
                    GetResponse response = channel.basicGet(queue, false);

                    while (response == null) {
                        response = channel.basicGet(queue, false);
                        Thread.sleep(MqConstants.ONE_SECOND);
                    }

                    Message message = new Message(response.getBody(),
                            messagePropertiesConverter.toMessageProperties(response.getProps(), response.getEnvelope(), "UTF-8"));
                    //2 将原始数据转换为特定类型的包
                    T messageBean = (T) messageConverter.fromMessage(message);

                    //3 处理数据
                    DetailResponse detailRes;

                    try {
                        detailRes = messageProcess.process(messageBean);
                    } catch (Exception e) {
                        log.error("exception", e);
                        detailRes = new DetailResponse(false, ConsumerResultEnum.FAIL.getCode(),e.getMessage());
                    }

                    //4 手动发送ack确认
                    if (detailRes.isSuccess()) {
                        channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
                    } else {
                        //避免过多失败log
                        Thread.sleep(MqConstants.ONE_SECOND);
                        log.info("process message failed: " + detailRes.getMsg());
                        channel.basicNack(response.getEnvelope().getDeliveryTag(), false, true);
                    }

                    return detailRes;
                } catch (InterruptedException e) {
                    log.error("exception", e);
                    return new DetailResponse(false, ConsumerResultEnum.FAIL.getCode(),e.getMessage());
                } catch (ShutdownSignalException | ConsumerCancelledException | IOException e) {
                    log.error("exception", e);

                    try {
                        channel.close();
                    } catch (IOException | TimeoutException ex) {
                        log.error("exception", ex);
                    }
                    channel = connection.createChannel(false);
                    return new DetailResponse(false, ConsumerResultEnum.FAIL.getCode(),e.getMessage());
                } catch (Exception e) {
                    log.info("exception : ", e);
                    try {
                        channel.close();
                    } catch (IOException | TimeoutException ex) {
                        ex.printStackTrace();
                    }
                    channel = connection.createChannel(false);
                    return new DetailResponse(false, ConsumerResultEnum.FAIL.getCode(),e.getMessage());
                }
            }
        };
    }


    private void buildQueue(String exchange, String routingKey,
                            final String queue, Connection connection, String type) throws IOException {
        Channel channel = connection.createChannel(false);

        if (type.equals(MqConstants.DIRECT_TYPE)) {
            channel.exchangeDeclare(exchange, MqConstants.DIRECT_TYPE, true, false, null);
        } else if (type.equals(MqConstants.TOPIC_TYPE)) {
            channel.exchangeDeclare(exchange, MqConstants.TOPIC_TYPE, true, false, null);
        }

        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey);

        try {
            channel.close();
        } catch (TimeoutException e) {
            log.info("close channel time out ", e);
        }
    }
}

package com.sales.rabbitmq.cousumer;

import com.sales.rabbitmq.common.DetailResponse;
import com.sales.rabbitmq.common.MqConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/8/2 14:53
 */
@Slf4j
public class ThreadPoolConsumer<T> {
    private ExecutorService executor;
    private volatile boolean stop = false;
    private final ThreadPoolConsumerBuilder<T> infoHolder;

    //构造器
    public static class ThreadPoolConsumerBuilder<T> {
        int threadCount;
        long intervalMils;
        FastBuildRabbitMqConsumer fastMqConsumer;
        String exchange;
        String routingKey;
        String queue;
        String type = "direct";
        MessageProcess<T> messageProcess;

        public ThreadPoolConsumerBuilder<T> setThreadCount(int threadCount) {
            this.threadCount = threadCount;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setIntervalMils(long intervalMils) {
            this.intervalMils = intervalMils;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMQAccessBuilder(FastBuildRabbitMqConsumer fastMqConsumer) {
            this.fastMqConsumer = fastMqConsumer;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setExchange(String exchange) {
            this.exchange = exchange;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setRoutingKey(String routingKey) {
            this.routingKey = routingKey;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setQueue(String queue) {
            this.queue = queue;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setType(String type) {
            this.type = type;

            return this;
        }

        public ThreadPoolConsumerBuilder<T> setMessageProcess(MessageProcess<T> messageProcess) {
            this.messageProcess = messageProcess;

            return this;
        }

        public ThreadPoolConsumer<T> build() {
            return new ThreadPoolConsumer<T>(this);
        }
    }

    private ThreadPoolConsumer(ThreadPoolConsumerBuilder<T> threadPoolConsumerBuilder) {
        this.infoHolder = threadPoolConsumerBuilder;
        executor = Executors.newFixedThreadPool(threadPoolConsumerBuilder.threadCount);
    }

    //1 构造messageConsumer
    //2 执行consume
    public void start() throws IOException {
        for (int i = 0; i < infoHolder.threadCount; i++) {
            //1
            final MessageConsumer messageConsumer = infoHolder.fastMqConsumer.buildMessageConsumer(infoHolder.exchange,
                    infoHolder.routingKey, infoHolder.queue, infoHolder.messageProcess, infoHolder.type);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (!stop) {
                        try {
                            //2
                            DetailResponse detailRes = messageConsumer.consume();

                            if (infoHolder.intervalMils > 0) {
                                try {
                                    Thread.sleep(infoHolder.intervalMils);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    log.info("interrupt ", e);
                                }
                            }

                            if (!detailRes.isSuccess()) {
                                log.info("run error " + detailRes.getMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("run exception ", e);
                        }
                    }
                }
            });
        }

        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public void stop() {
        this.stop = true;

        try {
            Thread.sleep(MqConstants.ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

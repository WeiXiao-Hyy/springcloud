package com.alipay.orderservice.mq.consumer;

import com.alipay.orderservice.mq.listener.OrderListener;
import javax.annotation.PostConstruct;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-02-17 09:58
 */
@Component
public class Consumer {
    private static final String consumerGroup = "consumer-group";

    private DefaultMQPushConsumer consumer;

    @Autowired
    private OrderListener orderListener;

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr("172.16.7.1:9876");
        consumer.subscribe("order", "*");
        consumer.registerMessageListener(orderListener);
        consumer.start();
    }
}
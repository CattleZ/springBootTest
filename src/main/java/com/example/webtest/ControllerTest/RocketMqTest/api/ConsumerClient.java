package com.example.webtest.ControllerTest.RocketMqTest.api;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

import javax.annotation.Resource;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 13:34
 **/
public class ConsumerClient {
    @Resource
    MqConfig mqConfig;
    @Resource
    ConsumerMessageListener consumerMessageListener;
    public void consumerMessage() throws MQClientException {
        // 创建接收消息的对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(mqConfig.getGroupName());
        consumer.setNamesrvAddr(mqConfig.getNameSvrAddr());
        consumer.subscribe(mqConfig.getTopic(),"*");
        consumer.registerMessageListener(consumerMessageListener);
        consumer.start();
    }
}

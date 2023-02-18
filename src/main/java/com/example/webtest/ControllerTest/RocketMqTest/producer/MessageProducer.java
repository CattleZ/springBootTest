package com.example.webtest.ControllerTest.RocketMqTest.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 10:52
 **/
public interface MessageProducer {
    SendResult doPush(DefaultMQProducer defaultMQProducer,String topic,String tag,Object object);
}

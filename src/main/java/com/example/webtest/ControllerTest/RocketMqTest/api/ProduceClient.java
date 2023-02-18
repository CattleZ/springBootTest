package com.example.webtest.ControllerTest.RocketMqTest.api;

import com.example.webtest.ControllerTest.RocketMqTest.producer.DefaultMessageProducer;
import com.example.webtest.ControllerTest.RocketMqTest.producer.MQUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;

import javax.annotation.Resource;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 12:35
 **/
public class ProduceClient {
    @Resource
    DefaultMessageProducer defaultMessageProducer;
    @Resource
    MQUtil mqUtil;
    @Resource
    MqConfig config;
    public SendResult producerMessage(Object object){
        DefaultMQProducer producer = new DefaultMQProducer(config.getGroupName());
        producer.setRetryTimesWhenSendFailed(3);
        producer.setNamesrvAddr(config.getNameSvrAddr());
        return mqUtil.push(producer,defaultMessageProducer,config.getTopic(),"xx",object);
    }
}

package com.example.webtest.ControllerTest.RocketMqTest.producer;


import jodd.exception.ExceptionUtil;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 12:23
 **/
@Component
public class MQUtil {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    public SendResult push(DefaultMQProducer defaultMQProducer,MessageProducer messageProducer,String topic,String tag,Object object){
        try{
            defaultMQProducer.start();
            Assert.notNull(messageProducer,"message为空");
            Assert.notNull(defaultMQProducer,"producer为空");
            return messageProducer.doPush(defaultMQProducer,topic,tag,object);
        }catch (MQClientException e){
            log.error("MQCLient异常{}", ExceptionUtil.exceptionStackTraceToString(e));
            Thread.currentThread().interrupt();
        }
        return new SendResult();
    }
}

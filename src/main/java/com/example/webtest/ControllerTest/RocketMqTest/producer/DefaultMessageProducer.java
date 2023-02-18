package com.example.webtest.ControllerTest.RocketMqTest.producer;

import jodd.exception.ExceptionUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 10:53
 **/
public class DefaultMessageProducer implements MessageProducer{
    protected final Logger log =LoggerFactory.getLogger(getClass());
    @Override
    public SendResult doPush(DefaultMQProducer defaultMQProducer, String topic, String tag, Object object) {
        log.debug("开始推送消息topic {},tag {} ,clz{}",topic,tag,object.getClass().getName());
        String message= Objects.toString(object);
        log.debug("content:{}",message);
        Message sendMsg = new Message(topic,tag,message.getBytes(StandardCharsets.UTF_8));
        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(sendMsg);

        } catch (MQClientException | MQBrokerException | RemotingException | InterruptedException e) {
            log.error("消息推送失败 {}", ExceptionUtil.exceptionStackTraceToString(e));
            Thread.currentThread().interrupt();
        }
        return sendResult;
    }
}

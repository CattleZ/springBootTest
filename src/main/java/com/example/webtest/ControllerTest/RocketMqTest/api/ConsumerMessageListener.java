package com.example.webtest.ControllerTest.RocketMqTest.api;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 13:47
 **/
public class ConsumerMessageListener implements MessageListenerConcurrently {
    @Resource
    ConsumerDo consumerDo;
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        // 这里写一个方法处理消息
        consumerDo.doRecive(list);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

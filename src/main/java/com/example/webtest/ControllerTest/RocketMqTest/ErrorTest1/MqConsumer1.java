package com.example.webtest.ControllerTest.RocketMqTest.ErrorTest1;

import com.example.webtest.ControllerTest.RocketMqTest.api.ConsumerMessageListener;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/5/6 20:43
 **/
public class MqConsumer1 {
    public static void main(String[] args) throws MQClientException {
        // 创建接收消息的对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Group_c");
        consumer.setNamesrvAddr("192.168.0.102:9876");
        consumer.subscribe("topic1","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @SneakyThrows
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt messageExt : list){
                    if(messageExt.getReconsumeTimes()>1){
                        continue;
                    }
                    try {
                        System.out.println("0--topic1--x"+(new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET)));
                    } catch (java.io.UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}

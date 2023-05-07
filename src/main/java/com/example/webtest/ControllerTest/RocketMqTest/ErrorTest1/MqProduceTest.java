package com.example.webtest.ControllerTest.RocketMqTest.ErrorTest1;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 生产者生产消息
 * @Author gorge
 * @Version 1.0
 * @Date 2023/5/6 20:43
 **/
public class MqProduceTest {
    public static DefaultMQProducer producer = new DefaultMQProducer("Group_produce");
    @Resource
    public static void main(String[] args) throws MQClientException {
        Scanner sc = new Scanner(System.in);
        producer.setRetryTimesWhenSendFailed(3);
        producer.setNamesrvAddr("192.168.0.102:9876");
        producer.start();
        String topic="";
        String message="";
        System.out.println("开始启动：");
        while(true){
            System.out.println("请输入topic:");
            topic = sc.next();
            System.out.println("请输入消息体:");
            message = sc.next();
            sendMessage(topic,message);
        }
    }
    public static void sendMessage(String topic,String message){
        System.out.println("topic:"+topic);
        Message sendMsg = new Message(topic,"*",message.getBytes(StandardCharsets.UTF_8));
        SendResult sendResult = null;
        try {
            sendResult = producer.send(sendMsg);
            System.out.println(topic+"end");
        } catch (MQClientException | MQBrokerException | RemotingException | InterruptedException e) {
            System.out.println(e);
            Thread.currentThread().interrupt();
        }
    }
}

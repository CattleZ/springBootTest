package com.example.webtest.ControllerTest.RabblitMqTest;

import com.rabbitmq.client.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2022/12/16 12:22
 **/
@RestController
public class Consumer {
    private  final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        consumerTest();
    }
    public static void consumerTest() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.107");
        factory.setPort(5673);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息");
        // 推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag,delivery)->{
            String message = new String(delivery.getBody());
            System.out.println(message);
        };
        // 取消消费的一个回调接口，如在消费到的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消费的消息被中断");
        };
        /**
         * 消费者消费消息
         * 1. 消费哪个队列
         * 2. 消费成功后是否要自动应答 true表示自动应答，false代表手动应答
         * 3. 消费者未成功消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}

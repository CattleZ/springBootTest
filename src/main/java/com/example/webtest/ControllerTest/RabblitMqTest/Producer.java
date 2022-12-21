package com.example.webtest.ControllerTest.RabblitMqTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2022/12/15 16:16
 **/
@RestController
public class Producer {
    private final static String QUEUE_NAME = "hello";

    @Test
    public void producerTest() throws Exception{
        // 创建一个工厂连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置连接的主机名
        factory.setHost("192.168.0.107");
        factory.setPort(5673);
        factory.setVirtualHost("/");
        // 设置连接用户名
        factory.setUsername("guest");
        // 设置密码
        factory.setPassword("guest");
        // channel实现了自动close接口，自动关闭，不需要显示关闭
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
        ){
            /**
             * 生成一个队列
             * 1. 队列名称
             * 2. 队列里面的消息是否持久化 默认消息存储在内存中
             * 3. 该队列是否只提供一个消费者进行消费 是否进行共享true 可以多个消费者消费
             * 4. 是否自动删除 最后一个消费者断开连接后，该队列是否自动删除 true 自动删除
             * 5. 其他参数
             */
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "hello world";
            /**
             * 发送一个消息，
             * 1. 发送到哪个交换机
             * 2. 路由的key 是哪个
             * 3. 其他的参数信息
             * 4. 发送消息的消息体
             */
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完毕");
        }
    }
}

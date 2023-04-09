package com.example.webtest.ControllerTest.RocketMqTest.api;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 12:31
 **/
@Configuration
@Data
public class MqConfig {
    /**
     * 配置文件配置
     */
//    @Value("${xx}")
    private String groupName;
//    @Value("${xx}")
    private String nameSvrAddr;
//    @Value("${xx}")
    private String topic;
}

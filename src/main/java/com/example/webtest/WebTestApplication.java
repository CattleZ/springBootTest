package com.example.webtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class) 排除maven中的配置
@SpringBootApplication()
@MapperScan("com.example.webtest.mapper")
public class WebTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebTestApplication.class, args);
    }
}

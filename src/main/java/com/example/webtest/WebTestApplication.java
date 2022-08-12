package com.example.webtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTestApplication.class, args);
    }

}

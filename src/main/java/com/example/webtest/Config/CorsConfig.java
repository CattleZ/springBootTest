package com.example.webtest.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 配置全局的同源策略
@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 放行哪些域名，可以多个
        .allowCredentials(true) // 是否发送cookie信息
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS","PATCH")// 放行哪些请求方式
        .allowedHeaders("*") // 放行哪些原始域（头部信息）
                .exposedHeaders("") // 暴露哪些头部信息 （因为跨域问题默认不能获取全部头部信息）
                .maxAge(3600); // 预请求的结果有效期，默认1800分钟，3600是一个小时
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}

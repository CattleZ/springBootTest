package com.example.webtest.ControllerTest.RocketMqTest.api;

import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/2/18 13:55
 **/
public interface ConsumerDo {
    public Object doRecive(List<MessageExt> list);
}

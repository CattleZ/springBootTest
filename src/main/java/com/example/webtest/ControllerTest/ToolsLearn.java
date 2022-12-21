package com.example.webtest.ControllerTest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2022/9/13 23:04
 **/
@RestController
@RequestMapping("/toolsLearn")
public class ToolsLearn {
    @RequestMapping("/learn")
    public void learn(){
    //启动10个线程
        IntStream.rangeClosed(1, 10).mapToObj(i -> new Thread(() -> {
            while (true) {
                //每一个线程都是一个死循环，休眠10秒，打印10M数据
                String payload = IntStream.rangeClosed(1, 10000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(payload.length());
            }
        })).forEach(Thread::start);
    }
}

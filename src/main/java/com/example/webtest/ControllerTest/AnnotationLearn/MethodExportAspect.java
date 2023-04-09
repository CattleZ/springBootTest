package com.example.webtest.ControllerTest.AnnotationLearn;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/4/9 12:50
 * 自定义注解的切面类
 **/
//关键代码一，说明当前对象是一个切面
@Aspect
// 关键代码二，允许在spring IOC对象实例化管理
@Component
@Slf4j
public class MethodExportAspect {
    // 关键代码三，说明切面的作用范围，任何增加@MethodExport注解的目标方法，都将在执行方法前先质执行该切面方法
    // @Around环绕通知，最强的对的通知类型，可以控制方法入参、执行、返回结果等各方面细节
    @Around("@annotation(com.example.webtest.ControllerTest.AnnotationLearn.MethodExport)")
    public Object methodExporter(ProceedingJoinPoint joinPoint) throws Throwable{
        long st = System.currentTimeMillis();
        // joinpoint 连接点 执行目标方法，获取方法返回值
        Object proceed = joinPoint.proceed();
        long et = System.currentTimeMillis();
        ObjectMapper mapper = new ObjectMapper();
        // 将入参json序列化
        System.out.println("joinPoint.getArgs()"+joinPoint.getArgs());
        String jsonParam = mapper.writeValueAsString(joinPoint.getArgs());
        // 将返回结果json序列化
        String jsonResult = null;
        if(proceed != null){
            jsonResult = mapper.writeValueAsString(proceed);
        }else{
            jsonResult = "null";
        }
        // 模拟信息处理上报过程
        log.info("正在上报服务器：\ntarget:{}.{}()\nexecution:{}ms,\nparameter:{}\nresult:{}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(),
                (et-st),
                jsonParam,
                jsonResult);
        return proceed;
    }
}

package com.example.webtest.ControllerTest.AnnotationLearn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/4/9 14:27
 **/
public class ExecutionTimeHandler implements InvocationHandler {
    private Object target;

    public ExecutionTimeHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println(method.getName() + " 方法执行时间：" + (endTime - startTime) + "ms");
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.name()+":"+myAnnotation.value());
        }
        return result;
    }
}

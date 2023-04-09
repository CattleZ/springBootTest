package com.example.webtest.ControllerTest.AnnotationLearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现注解方法
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface MyAnnotationMethod {
    // 返回类型
    Class<?> returnType();
    // 方法名称
    String methodName();
    // 参数类型
    Class<?> [] parameterTypes();

}

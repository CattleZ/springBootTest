package com.example.webtest.ControllerTest.AnnotationLearn;

import org.junit.Test;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/4/8 20:38
 **/
public class AnnotationTest {
    @MyAnnotation(value = "example")
    @MyAnnotationArray(values = {"example1","example2"})
    @MyAnnotationClass(myClass = MyClass.class)
    @MyAnnotationMethod(returnType = String.class,methodName = "myMethod",parameterTypes = {int.class,String.class})
    @Test
    void testAnnotation(){
        System.out.println("test");
    }
}

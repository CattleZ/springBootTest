package com.example.webtest.ControllerTest.AnnotationLearn;


import java.lang.reflect.Proxy;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/4/8 20:38
 **/
public class AnnotationTest implements AnnotationTestinte{
    @MyAnnotation(value = "example")
    @MyAnnotationArray(values = {"example1","example2"})
    @MyAnnotationClass(myClass = MyClass.class)
    @MyAnnotationMethod(returnType = String.class,methodName = "myMethod",parameterTypes = {int.class,String.class})
    @Override
    public void testAnnotation(){
        System.out.println("test");
    }

    // 通过反射的技术实现注解的解析
    public static void main(String[] args) {
        AnnotationTestinte demo = new AnnotationTest();
        ExecutionTimeHandler handler = new ExecutionTimeHandler(demo);
        AnnotationTestinte proxy = (AnnotationTestinte) Proxy.newProxyInstance(
                AnnotationTestinte.class.getClassLoader(),
                new Class[]{AnnotationTestinte.class},
                handler);
        proxy.testAnnotation();
    }
}

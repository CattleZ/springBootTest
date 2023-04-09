package com.example.webtest.ControllerTest.AnnotationLearn;


import java.lang.annotation.*;

/**
 * 需要添加元注解（共有四个元注解）
 * @Retention 用于指定注解的保留策略，即注解应该保留在什么时候，
 *            RetentionPolicy.RUNTIME，这意味着注解应该在运行时可用
 * @Target 用于指定注解可以应用于哪些类型的程序元素，
 *         ElementType.METHOD，这意味着注解可以应用于方法
 * @Inherited 标明这个注解是可以被继承的
 * @Documented 在生成javadoc的时候就会把@Documented注解给显示出来。只是用来做标识，没什么实际作用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    /**
     * 可以添加自定义元素
      */
    // 任何基本类型

    // String类型
    String value();
    String name() default "zhangsan"; // 自定义注解并设置默认值
}

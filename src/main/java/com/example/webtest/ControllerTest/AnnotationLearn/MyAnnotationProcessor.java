package com.example.webtest.ControllerTest.AnnotationLearn;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/4/9 12:17
 * 自定义注解处理器
 **/
@SupportedAnnotationTypes("com.example.webtest.ControllerTest.AnnotationLearn.MyAnnotation")
public class MyAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("testannotations:"+annotations);
        return true;
    }
}

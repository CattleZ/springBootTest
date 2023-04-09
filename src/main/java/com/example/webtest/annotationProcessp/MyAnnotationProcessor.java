package com.example.webtest.annotationProcessp;

import org.springframework.stereotype.Component;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @Author gorge
 * @Version 1.0
 * @Date 2023/4/9 12:17
 * 自定义注解处理器
 **/
@Component
@SupportedAnnotationTypes("com.example.webtest.ControllerTest.AnnotationLearn.MyAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyAnnotationProcessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("testannotations:"+annotations);
        return true;
    }
}

package com.example.designmodel.singleton;

/**
 * 饿汉模式
 * @author zhanghe5
 */

public class EagerSingletonSun {
    private static final EagerSingletonSun instance = new EagerSingletonSun();

    private EagerSingletonSun(){}

    public static EagerSingletonSun getInstance(){
        return instance;
    }
}

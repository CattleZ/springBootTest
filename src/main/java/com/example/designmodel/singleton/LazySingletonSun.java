package com.example.designmodel.singleton;

public class LazySingletonSun {
    // 静态变量
    private volatile static LazySingletonSun instance;

    private LazySingletonSun() {
        // 私有构造函数，防止外部实例化
    }

    public static LazySingletonSun getInstance() {
        if (instance == null) {
            synchronized (LazySingletonSun.class) {
                if (instance == null) {
                    instance = new LazySingletonSun();
                }
            }
        }
        return instance;
    }
}

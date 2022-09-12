package com.example.webtest.entity;


import lombok.Getter;

public class Data {

    @Getter
    private static int counter  = 0;


    public static int reset() {
        counter = 0;
        return counter;
    }
    // counter属于类实例，此种加锁方法只保证了同一个类实例在不同的线程上，不会同时执行wrong方法
    public synchronized void wrong(){
        counter++;
    }
    //  修正方法(在类中定义一个Object 类型的静态字段，在操作count之前对这个字段加锁)
    private static Object locker = new Object();

    public void right(){
        synchronized (locker){
            counter++;
        }
    }
}

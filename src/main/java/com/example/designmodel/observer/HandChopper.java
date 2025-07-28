package com.example.designmodel.observer;

import java.util.concurrent.FutureTask;

public class HandChopper extends Buyer{
    public HandChopper(String name) {
        super(name);
    }

    @Override
    public void inform(String productName) {
        System.out.println("手抓饼店：" + this.name + "，被通知了，正在购买" + productName);
    }
}

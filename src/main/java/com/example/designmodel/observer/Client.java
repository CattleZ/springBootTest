package com.example.designmodel.observer;

public class Client {
    public static void main(String[] args) {
        Shop shop = new Shop();
        PhoneFans phoneFans = new PhoneFans("张三");
        HandChopper handChopper = new HandChopper("李四");
        shop.regist(phoneFans);
        shop.regist(handChopper);
        shop.setProductName("苹果手机");
        shop.setProductName("电脑");
        shop.setProductName("手电筒");
    }
}

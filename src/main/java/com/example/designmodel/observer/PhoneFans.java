package com.example.designmodel.observer;

public class PhoneFans extends Buyer{
    public PhoneFans(String name) {
        super(name);
    }

    @Override
    public void inform(String productName) {
        if ("苹果手机".equals(productName)) {
            System.out.println("手机粉丝：" + this.name + "，被通知了，正在购买手机");
        }
    }
}

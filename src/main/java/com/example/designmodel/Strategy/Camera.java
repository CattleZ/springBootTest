package com.example.designmodel.Strategy;

public class Camera implements  USB{
    @Override
    public void read() {
        System.out.println("摄像头输入");
    }
}

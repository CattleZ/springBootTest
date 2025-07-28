package com.example.designmodel.Strategy;

public class Mouse implements USB{
    @Override
    public void read() {
        System.out.println("鼠标输入");
    }
}

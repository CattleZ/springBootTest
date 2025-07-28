package com.example.designmodel.interpreter;

public class LeftKeyUp implements  Expression{
    @Override
    public void interpret() {
        System.out.println("左键抬起");
    }
}

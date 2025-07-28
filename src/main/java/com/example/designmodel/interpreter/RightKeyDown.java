package com.example.designmodel.interpreter;

public class RightKeyDown implements  Expression{
    @Override
    public void interpret() {
        System.out.println("右键按下");
    }
}

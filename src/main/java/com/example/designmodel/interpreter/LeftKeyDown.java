package com.example.designmodel.interpreter;

public class LeftKeyDown implements Expression{
    @Override
    public void interpret() {
        System.out.println("左键按下");
    }
}

package com.example.designmodel.interpreter;

public class Delay implements  Expression{

    private int second;
    public Delay(int second){
        this.second = second;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public void interpret() {
        System.out.println("延时 " + second + " 秒");
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

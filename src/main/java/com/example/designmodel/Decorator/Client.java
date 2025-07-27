package com.example.designmodel.Decorator;

public class Client {
    public static void main(String[] args) {
        ShowAble showAble = new FundationMakeUp(new Lipstick(new Girl()));
        showAble.show();
    }
}

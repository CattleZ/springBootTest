package com.example.designmodel.Decorator;

public class Lipstick extends Decorator {

    public Lipstick(ShowAble showAble) {
        super(showAble);
    }

    @Override
    public void show() {
        System.out.println("涂口红【");
        super.show();
        System.out.println("】");
    }
}

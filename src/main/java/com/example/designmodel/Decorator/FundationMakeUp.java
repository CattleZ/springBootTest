package com.example.designmodel.Decorator;

public class FundationMakeUp extends Decorator{
    public FundationMakeUp(ShowAble showAble) {
        super(showAble);
    }
    @Override
    public void show() {
        System.out.println("打粉底【");
        super.show();
        System.out.println("】");
    }
}

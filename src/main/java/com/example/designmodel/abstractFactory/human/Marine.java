package com.example.designmodel.abstractFactory.human;

import com.example.designmodel.abstractFactory.LowClassUnit;

public class Marine extends LowClassUnit {

    public Marine(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("海军出现在位置 (" + x + ", " + y + ")");
    }

    @Override
    public void attack() {
        System.out.println("海军开始攻击! ");
    }
}

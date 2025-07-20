package com.example.designmodel.abstractFactory.monster;

import com.example.designmodel.abstractFactory.LowClassUnit;

public class Roach extends LowClassUnit {

    public Roach(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("蟑螂出现在位置 (" + x + ", " + y + ")");
    }

    @Override
    public void attack() {
        System.out.println("蟑螂攻击");
    }
}

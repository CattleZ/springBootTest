package com.example.designmodel.abstractFactory.human;

import com.example.designmodel.abstractFactory.MiddleClassUnit;

public class Tank extends MiddleClassUnit {

    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("坦克出现在位置 (" + x + ", " + y + ")");
    }

    @Override
    public void attack() {
        System.out.println("坦克攻击");
    }
}

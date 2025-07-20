package com.example.designmodel.abstractFactory.monster;

import com.example.designmodel.abstractFactory.MiddleClassUnit;

public class Poison extends MiddleClassUnit {
    public Poison(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("毒虫出现在位置 (" + x + ", " + y + ")");
    }

    @Override
    public void attack() {
        System.out.println("毒虫攻击力");
    }
}

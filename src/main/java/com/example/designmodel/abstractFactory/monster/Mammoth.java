package com.example.designmodel.abstractFactory.monster;

import com.example.designmodel.abstractFactory.HighClassUnit;

public class Mammoth extends HighClassUnit {

    public Mammoth(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("猛犸象出现在位置 (" + x + ", " + y + ")");
    }

    @Override
    public void attack() {
        System.out.println("猛犸象攻击");
    }
}

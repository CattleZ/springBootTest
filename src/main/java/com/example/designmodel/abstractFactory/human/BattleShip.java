package com.example.designmodel.abstractFactory.human;

import com.example.designmodel.abstractFactory.HighClassUnit;

public class BattleShip extends HighClassUnit {
    public BattleShip(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("战舰出现在位置 (" + x + ", " + y + ")");
    }

    @Override
    public void attack() {
        System.out.println("战舰攻击");
    }
}

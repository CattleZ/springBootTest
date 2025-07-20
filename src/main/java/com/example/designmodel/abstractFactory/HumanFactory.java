package com.example.designmodel.abstractFactory;

import com.example.designmodel.abstractFactory.human.BattleShip;
import com.example.designmodel.abstractFactory.human.Marine;
import com.example.designmodel.abstractFactory.human.Tank;

public class HumanFactory implements AbstractFactory{
    private int x;
    private int y;

    public HumanFactory(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public HighClassUnit createHighClassUnit() {
        return new BattleShip(x, y);
    }

    @Override
    public LowClassUnit createLowClassUnit() {
        return new Marine(x, y);
    }

    @Override
    public MiddleClassUnit createMiddleClassUnit() {
        return new Tank(x, y);
    }
}

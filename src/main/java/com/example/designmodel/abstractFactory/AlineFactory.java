package com.example.designmodel.abstractFactory;

import com.example.designmodel.abstractFactory.monster.Mammoth;
import com.example.designmodel.abstractFactory.monster.Poison;
import com.example.designmodel.abstractFactory.monster.Roach;

public class AlineFactory implements AbstractFactory{
    private int x;
    private int y;
    @Override
    public HighClassUnit createHighClassUnit() {
        return new Mammoth(x,y);
    }

    @Override
    public LowClassUnit createLowClassUnit() {
        return new Roach(x,y);
    }

    @Override
    public MiddleClassUnit createMiddleClassUnit() {
        return new Poison(x,y);
    }
}

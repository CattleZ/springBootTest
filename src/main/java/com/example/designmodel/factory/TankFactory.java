package com.example.designmodel.factory;

import java.util.Random;

public class TankFactory implements  Factory{
    @Override
    public Enemy createEnemy(int screenWidth) {
        return new Tank(new Random(screenWidth).nextInt(),0);
    }
}

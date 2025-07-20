package com.example.designmodel.factory;

import java.util.Random;

public class AriPlaneFactory implements Factory{
    @Override
    public Enemy createEnemy(int screenWidth) {
        int x = new Random().nextInt(screenWidth);
        return new AirPlane(x,0);
    }
}

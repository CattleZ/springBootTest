package com.example.designmodel.factory;

import java.util.Random;

public class SimpleFactory {
    private int screenWidth; // 屏幕宽度
    private Random random ; // 随机数
    public SimpleFactory(int screenWidth)
    {
        this.screenWidth = screenWidth;
        random = new Random();
    }

    public Enemy createEnemy(String type)
    {
        int x = random.nextInt(screenWidth);
        switch (type){
            case "Tank":
                return new Tank(x,0);
            case "AirPlane":
                return new AirPlane(x,0);
            default:
                return null;
        }
    }
}

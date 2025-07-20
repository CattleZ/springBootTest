package com.example.designmodel.factory;

public class AirPlane extends Enemy {

    public AirPlane(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("AirPlane is flying!");
        System.out.println("AirPlane at position (" + x + ", " + y + ")");
    }
}

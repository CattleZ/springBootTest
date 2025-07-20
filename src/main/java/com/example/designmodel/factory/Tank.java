package com.example.designmodel.factory;

public class Tank extends Enemy {

    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("Tank is rolling!");
        System.out.println("Tank at position (" + x + ", " + y + ")");
    }
}

package com.example.designmodel.flyweight;

public class Client {
    public static void main(String[] args) {
        TileFactory tileFactory = new TileFactory();
        tileFactory.getTile("grass").draw(0, 0);
        tileFactory.getTile("grass").draw(1, 0);
        tileFactory.getTile("river").draw(1, 1);
        tileFactory.getTile("river").draw(2, 1);
        tileFactory.getTile("house").draw(2, 2);
        tileFactory.getTile("house").draw(3, 2);
        tileFactory.getTile("road").draw(3, 3);
        tileFactory.getTile("road").draw(4, 3);
    }
}

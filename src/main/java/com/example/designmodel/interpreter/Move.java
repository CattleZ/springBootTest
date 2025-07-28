package com.example.designmodel.interpreter;

public class Move implements Expression{
    private int x,y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void interpret() {
        System.out.println("移动到("+x+","+y+")");
    }

}

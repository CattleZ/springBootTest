package com.example.designmodel.abstractFactory;

public abstract class Unit {
    protected int x;
    protected int y;
    protected int attack;
    protected int defense;
    protected int health;

    public Unit(int x, int y, int attack, int defense, int health)
    {
        this.x = x;
        this.y = y;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }
    public abstract void show();

    public abstract void attack();
}

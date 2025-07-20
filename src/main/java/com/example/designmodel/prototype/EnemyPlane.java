package com.example.designmodel.prototype;

public class EnemyPlane implements Cloneable { // 实现Cloneable接口
    private int x;
    private int y =0;

    public EnemyPlane(int x) {
        this.x = x;
    }
    public void fly() {
        y++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public EnemyPlane clone() throws CloneNotSupportedException {
        return (EnemyPlane)super.clone(); // 调用Object的clone方法
    }
}

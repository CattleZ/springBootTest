package com.example.designmodel.flyweight;

public class Grass implements Drawable{
    private String imageName;

    public Grass(String imageName)
    {
        this.imageName = imageName;
        System.out.println("加载草地图片：" + imageName + " 耗费时间20ms");
    }
    @Override
    public void draw(int x, int y) {
        System.out.println("绘制草地图片：" + imageName +
                "，位置：(" + x + ", " + y + ")" );
    }
}

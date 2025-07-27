package com.example.designmodel.flyweight;

public class Road implements Drawable
{
    private String imageName;

    public Road(String imageName) {
        this.imageName = imageName;
        System.out.println("加载道路图片：" + imageName + " 耗费时间50ms");
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("绘制道路图片：" + imageName +
                "，位置：(" + x + ", " + y + ")");
    }
}

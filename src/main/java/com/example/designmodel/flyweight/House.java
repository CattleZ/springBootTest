package com.example.designmodel.flyweight;

public class House implements Drawable
{
    private String imageName;

    public House(String imageName) {
        this.imageName = imageName;
        System.out.println("加载房屋图片：" + imageName + " 耗费时间40ms");
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("绘制房屋图片：" + imageName +
                "，位置：(" + x + ", " + y + ")");
    }
}

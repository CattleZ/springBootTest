package com.example.designmodel.flyweight;

public class River implements Drawable{
    private String imageName;

    public River(String imageName) {
        this.imageName = imageName;
        System.out.println("加载河流图片：" + imageName+"耗费时间30ms");
    }
    @Override
    public void draw(int x, int y) {
        System.out.println("绘制河流图片：" + imageName +
                "，位置：(" + x + ", " + y + ")");
    }
}

package com.example.designmodel.Bridge;

public class BlackPen extends  Pen {
    public BlackPen(Ruler ruler) {
        super(ruler);
    }
    @Override
    public void draw() {
        System.out.println("使用黑色笔开始绘制");
        ruler.regularize();
    }
}

package com.example.designmodel.Bridge;

public class WhitePen extends Pen{

    public WhitePen(Ruler ruler) {
        super(ruler);
    }

    @Override
    public void draw() {
        System.out.println("使用白色笔开始绘制");
        ruler.regularize();
    }
}

package com.example.designmodel.Bridge;

public class Client {
    public static void main(String[] args) {
        Ruler ruler = new SquareRuler();
        Ruler ruler1 = new CircleRuler();
        Ruler ruler2 = new TriangleRuler();
        Pen pen = new BlackPen(ruler);
        Pen pen1 = new WhitePen(ruler1);
        Pen pen2 = new BlackPen(ruler2);
        pen.draw();
        pen1.draw();
        pen2.draw();
    }
}

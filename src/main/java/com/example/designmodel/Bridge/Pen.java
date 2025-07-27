package com.example.designmodel.Bridge;

public abstract class Pen {
    protected Ruler ruler;

    public Pen(Ruler ruler)
    {
        this.ruler = ruler;
    }

    public abstract void draw();

}

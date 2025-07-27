package com.example.designmodel.Decorator;

public abstract class Decorator implements ShowAble{
    private ShowAble showAble;

    public Decorator(ShowAble showAble) {
        this.showAble = showAble;
    }

    @Override
    public void show() {
        showAble.show();
    }
}

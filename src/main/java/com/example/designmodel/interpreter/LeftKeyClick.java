package com.example.designmodel.interpreter;

public class LeftKeyClick implements  Expression{

    private LeftKeyUp leftKeyUp;
    private LeftKeyDown leftKeyDown;

    public LeftKeyClick() {
        this.leftKeyUp = new LeftKeyUp();
        this.leftKeyDown = new LeftKeyDown();
    }
    @Override
    public void interpret() {
        leftKeyDown.interpret();
        leftKeyUp.interpret();
    }
}

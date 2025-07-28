package com.example.designmodel.interpreter;

public class Repetition implements  Expression{

    private int loopCount;
    private Expression loopBodyExpression;

    public Repetition(int loopCount, Expression loopBodyExpression)
    {
        this.loopCount = loopCount;
        this.loopBodyExpression = loopBodyExpression;
    }

    @Override
    public void interpret() {
        while (loopCount > 0) {
            loopBodyExpression.interpret();
            loopCount--;
        }
    }
}

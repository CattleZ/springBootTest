package com.example.designmodel.interpreter;

import java.util.List;

public class Sequence implements Expression {

    private List<Expression> expressions;

    public Sequence(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void interpret() {
        for (Expression expression : expressions) {
            expression.interpret();
        }
    }
}

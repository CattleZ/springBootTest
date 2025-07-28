package com.example.designmodel.visitor;

public interface Visitor {

    void visit(Fruit fruit);

    void visit(Wine wine);

    void visit(Candy candy);
}

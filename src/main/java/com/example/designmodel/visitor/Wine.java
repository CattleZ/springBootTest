package com.example.designmodel.visitor;

import java.time.LocalDate;

public class Wine extends Product implements Acceptable{

    public Wine(String name, LocalDate productDate, double price) {
        super(name, productDate, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

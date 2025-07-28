package com.example.designmodel.visitor;



import java.time.LocalDate;

public class Fruit extends Product implements Acceptable{

    private float weight;

    public Fruit(String name, LocalDate productDate, double price, float weight) {
        super(name, productDate, price);
        this.weight = weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

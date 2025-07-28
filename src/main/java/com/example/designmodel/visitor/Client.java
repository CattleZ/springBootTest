package com.example.designmodel.visitor;

import java.time.LocalDate;

public class Client {

    public static void main(String[] args) {
        Fruit fruit = new Fruit("Apple", LocalDate.of(2019, 1, 1), 10.0, 1.0f);
        Wine wine = new Wine("Wine", LocalDate.of(2019, 1, 1), 20.0);
        Candy candy = new Candy("Candy", LocalDate.of(2019, 1, 1), 5.0);
        DisCountVisitor visitor = new DisCountVisitor(LocalDate.of(2019, 1, 1));
        fruit.accept(visitor);
        wine.accept(visitor);
        candy.accept(visitor);
    }
}

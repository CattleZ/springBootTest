package com.example.designmodel.visitor;

import java.time.LocalDate;

public abstract class Product {

    private String name;

    private LocalDate productDate;

    private double price;

    public Product(String name, LocalDate productDate, double price) {
        this.name = name;
        this.productDate = productDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductDate() {
        return productDate;
    }

    public void setProductDate(LocalDate productDate) {
        this.productDate = productDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

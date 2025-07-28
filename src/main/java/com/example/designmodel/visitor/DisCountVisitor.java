package com.example.designmodel.visitor;

import java.text.NumberFormat;
import java.time.LocalDate;

public class DisCountVisitor implements Visitor{

    private LocalDate billDate;

    public DisCountVisitor(LocalDate billDate) {
        this.billDate = billDate;
        System.out.println("结算日期："+ billDate);
    }
    @Override
    public void visit(Fruit fruit) {
        System.out.println("水果======【"+fruit.getName()+"】结算价格======");
        float rait =0;
        long days = billDate.toEpochDay() - fruit.getProductDate().toEpochDay();
        if (days > 7) {
            System.out.println("超过7天，¥0.0元，请勿食用");
        } else if (days > 3) {
            rait = 0.5f;
        } else {
            rait = 1f;
        }
        double price = fruit.getPrice() * rait* fruit.getWeight();

        System.out.println("水果======【"+fruit.getName()+"】结算价格======" + NumberFormat.getCurrencyInstance().format(price));
    }

    @Override
    public void visit(Wine wine) {
        System.out.println("酒水======【"+wine.getName()+"】无折算价格======");
        System.out.println("酒水======【"+wine.getName()+"】结算价格======" + NumberFormat.getCurrencyInstance().format(wine.getPrice()));
    }

    @Override
    public void visit(Candy candy) {
        System.out.println("糖果======【"+candy.getName()+"】结算价格======");
        float rait =0;
        long days = billDate.toEpochDay() - candy.getProductDate().toEpochDay();
        if (days > 180) {
            System.out.println("超过180天，¥0.0元，请勿食用");
        } else {
            rait = 1f;
        }
        double price = candy.getPrice() * rait;

        System.out.println("糖果======【"+candy.getName()+"】结算价格======" + NumberFormat.getCurrencyInstance().format(price));
    }
}

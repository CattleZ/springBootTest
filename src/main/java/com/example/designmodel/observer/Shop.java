package com.example.designmodel.observer;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String productName;

    private List<Buyer> buyers;

    public Shop() {
        this.productName = "无商品";
        this.buyers = new ArrayList<>();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyBuyers();
    }

    public void notifyBuyers() {
        for (Buyer buyer : this.buyers) {
            buyer.inform(this.productName);
        }
    }

    public void regist(Buyer buyer){
        this.buyers.add(buyer);
    }

}

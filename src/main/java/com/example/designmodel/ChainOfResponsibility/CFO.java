package com.example.designmodel.ChainOfResponsibility;

public class CFO extends Approver{
    public CFO(String name) {
        super(name);
    }

    @Override
    public Boolean approve(int amount) {
        if (amount <= 10000) {
            System.out.println(name + "审批通过，金额：" + amount);
            return true;
        } else {
            System.out.println(name + "审批不通过，金额：" + amount);
            return false;
        }
    }
}

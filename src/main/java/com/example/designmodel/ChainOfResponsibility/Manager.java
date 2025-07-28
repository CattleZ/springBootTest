package com.example.designmodel.ChainOfResponsibility;

public class Manager extends Approver {
    public Manager(String name) {
        super(name);
    }

    @Override
    public Boolean approve(int amount) {
        if (amount <= 5000) {
            System.out.println(name + "审批通过，金额：" + amount);
            return true;
        } else {
            System.out.println(name + "审批不通过，金额：" + amount);
            this.nextApprover.approve(amount);
            return false;
        }
    }

}

package com.example.designmodel.ChainOfResponsibility;

public class Staff extends Approver{

    public Staff(String name) {
        super(name);
    }
    @Override
    public Boolean approve(int amount) {
        if (amount <= 1000) {
            System.out.println(name + "审批通过，金额：" + amount);
            return true;
        } else {
            System.out.println(name + "审批未通过，金额：" + amount);
          this.nextApprover.approve(amount);
          return false;
        }
    }
}

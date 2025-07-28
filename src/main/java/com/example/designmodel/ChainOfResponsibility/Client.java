package com.example.designmodel.ChainOfResponsibility;

public class Client {
    public static void main(String[] args) {
        Approver staff = new Staff("小王");
        staff.setNextApprover(new Manager("小李"))
              .setNextApprover(new CFO("小张"));

        staff.approve(500);
        staff.approve(5000);
        staff.approve(12000);
    }

}

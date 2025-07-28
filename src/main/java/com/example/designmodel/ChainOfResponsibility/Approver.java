package com.example.designmodel.ChainOfResponsibility;

public abstract class Approver {
    protected String name;
    protected Approver nextApprover;

    public Approver(String name)
    {
        this.name = name;
    }

    public Approver setNextApprover(Approver approver)
    {
        this.nextApprover = approver;
        return this.nextApprover;
    }

    public abstract Boolean approve(int amount);
}

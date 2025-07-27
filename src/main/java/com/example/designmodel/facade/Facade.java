package com.example.designmodel.facade;

/**
 * 外观模式对外提供一个接口，内部调用多个子系统的方法，
 */
public class Facade {
    private Vegvendor vegvendor;
    private Chef chef;
    private Waiter waiter;
    private Cleaner cleaner;
    public Facade()
    {
        vegvendor = new Vegvendor();
        vegvendor.puchaseVeg();
        chef = new Chef();
        waiter = new Waiter();
        cleaner = new Cleaner();
    }

    public void order(){
        chef.makeFood();
        waiter.takeOrder();
        cleaner.clean();
    }
}

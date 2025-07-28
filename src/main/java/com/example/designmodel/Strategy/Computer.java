package com.example.designmodel.Strategy;

public class Computer {
    private USB usb;
    public void setUSB(USB usb)
    {
        this.usb = usb;
    }
    public void read()
    {
        usb.read();
    }
}

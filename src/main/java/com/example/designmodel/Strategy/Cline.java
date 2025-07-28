package com.example.designmodel.Strategy;

public class Cline {
    public static void main(String[] args) {
        Computer c = new Computer();
        c.setUSB(new Mouse());
        c.read();
        c.setUSB(new Camera());
        c.read();
        c.setUSB(new KeyBoard());
        c.read();
    }
}

package com.example.designmodel.Command;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        Switch switch1 = new Switch();
        Bulb bulb = new Bulb();
        Command switchCommand = new SwitchCommand(bulb);

        switch1.setCommand(switchCommand);
        switch1.buttonPush();
        switch1.buttonPop();

        Command flashCommand = new FlashCommand(bulb);
        switch1.setCommand(flashCommand);

        switch1.buttonPush();
        Thread.sleep(3000);
        switch1.buttonPop();
    }
}

package com.example.designmodel.Command;

public class Switch {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void buttonPush(){
        System.out.println("Button pushed, executing command...");
        command.exe();
    }

    public void buttonPop() {
        System.out.println("Button popped, undoing command...");
        command.unexe();
    }
}

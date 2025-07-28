package com.example.designmodel.Command;

/**
 * 开关命令实现类
 */
public class SwitchCommand implements Command{

    private Bulb bulb;

    public SwitchCommand(Bulb bulb) {
        this.bulb = bulb;
    }

    @Override
    public void exe() {
        bulb.on();
    }

    @Override
    public void unexe() {
        bulb.off();
    }
}

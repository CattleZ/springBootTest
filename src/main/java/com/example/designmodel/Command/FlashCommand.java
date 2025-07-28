package com.example.designmodel.Command;

public class FlashCommand implements Command {

    private Bulb bulb;
    private volatile boolean isFlashing = false;

    public FlashCommand(Bulb bulb) {
        this.bulb = bulb;
    }

    @Override
    public void exe() {
       if (!isFlashing) {
           isFlashing = true;
           System.out.println("Bulb started flashing");
           new Thread(() -> {
               try {
                   while(isFlashing){
                       bulb.on();
                       Thread.sleep(500);
                       bulb.off();
                       Thread.sleep(500);
                   }
               } catch (InterruptedException e){
                   e.printStackTrace();
               }
           }).start();
       }
    }

    @Override
    public void unexe() {
        isFlashing = false;
        System.out.println("Bulb stopped flashing");
    }
}

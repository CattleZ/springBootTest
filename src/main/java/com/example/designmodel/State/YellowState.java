package com.example.designmodel.State;


public class YellowState implements  State{
    @Override
    public void switchToGreen(TrafficLight trafficLight) {
        trafficLight.setState(new GreenState());
        System.out.println("黄灯变绿" +
                "，请车辆通行" +
                "，绿灯亮起" );
    }

    @Override
    public void switchToYellow(TrafficLight trafficLight) {
        System.out.println("ERROR! 黄灯已经亮着");
    }

    @Override
    public void switchToRed(TrafficLight trafficLight) {
        trafficLight.setState(new RedState());
        System.out.println("黄灯变红" +
                "，请车辆停车" +
                "，红灯亮起" );
    }
}

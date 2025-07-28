package com.example.designmodel.State;

public class RedState implements  State{
    @Override
    public void switchToGreen(TrafficLight trafficLight) {
        System.out.println("ERROR! 红灯不能直接变绿");
    }

    @Override
    public void switchToYellow(TrafficLight trafficLight) {
        trafficLight.setState(new YellowState());
        System.out.println("红灯变黄" +
                "，请车辆准备停车");
    }

    @Override
    public void switchToRed(TrafficLight trafficLight) {
        System.out.println("ERROR! 红灯已经亮着");
    }
}

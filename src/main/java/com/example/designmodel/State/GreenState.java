package com.example.designmodel.State;

public class GreenState implements  State{
    @Override
    public void switchToGreen(TrafficLight trafficLight) {
        System.out.println("ERROR! 绿灯已经亮着");
    }

    @Override
    public void switchToYellow(TrafficLight trafficLight) {
        trafficLight.setState(new YellowState());
        System.out.println( "绿灯变黄" +
                "，请车辆准备停车" );
    }

    @Override
    public void switchToRed(TrafficLight trafficLight) {
        System.out.println("ERROR! 绿灯不能直接变红");
    }
}

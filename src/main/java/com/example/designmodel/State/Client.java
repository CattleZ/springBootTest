package com.example.designmodel.State;

public class Client {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        // 红灯
        trafficLight.switchToRed();
        trafficLight.switchToYellow();
        trafficLight.switchToGreen();
    }
}

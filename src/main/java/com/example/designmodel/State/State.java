package com.example.designmodel.State;

public interface State {

    void switchToGreen(TrafficLight trafficLight);

    void switchToYellow(TrafficLight trafficLight);

    void switchToRed(TrafficLight trafficLight);
}

package com.example.designmodel.State;

public class TrafficLight{

    State state = new RedState();

    public void setState(State state){
        this.state = state;
    }

    public void switchToGreen()
    {
        state.switchToGreen(this);
    }

    public void switchToYellow()
    {
        state.switchToYellow(this);
    }

    public void switchToRed()
    {
        state.switchToRed(this);
    }
}

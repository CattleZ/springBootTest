package com.example.designmodel.Adapter;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class Adapter implements TriplePin{
    private DualPin pin;
    public Adapter(DualPin pin) {
        this.pin = pin;
    }
    @Override
    public void electrify(int l, int r, int e) {
        pin.electrify(l, r);
    }
}

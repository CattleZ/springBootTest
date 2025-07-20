package com.example.designmodel.abstractFactory;

public interface AbstractFactory {
    HighClassUnit createHighClassUnit();

    LowClassUnit createLowClassUnit();

    MiddleClassUnit createMiddleClassUnit();
}

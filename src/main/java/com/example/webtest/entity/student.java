package com.example.webtest.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class student {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

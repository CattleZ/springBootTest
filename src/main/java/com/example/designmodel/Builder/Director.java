package com.example.designmodel.Builder;

/**
 * 工程总监
 */
public class Director {

    private Builder builder;

    public Director(Builder builder)
    {
        this.builder = builder;
    }
    public void setBuilder(Builder builder)
    {
        this.builder = builder;
    }
    public Building direct()
    {
        System.out.println("项目工程启动");
        builder.buildBasic();
        builder.buildWall();
        builder.buildRoof();
        return builder.getBuilding();
    }
}

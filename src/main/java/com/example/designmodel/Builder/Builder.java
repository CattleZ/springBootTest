package com.example.designmodel.Builder;

/**
 * 施工方接口
 */
public interface Builder {

    /**
     * 建造地基
     */
    public void buildBasic();

    /**
     * 建造墙体
     */
    public void buildWall();

    /**
     * 建造屋顶
     */
    public void buildRoof();

    /**
     * 获取产品
     */
    public Building getBuilding();
}

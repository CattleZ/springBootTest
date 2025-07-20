package com.example.designmodel.Builder;

/**
 * 房屋建造者
 */
public class HouseBuilder implements Builder{
    private Building building;

    public HouseBuilder() {
        building = new Building();
    }

    @Override
    public void buildBasic() {
        building.buildBasic("普通地基");
    }

    @Override
    public void buildWall() {
        building.buildWall("普通墙");
    }

    @Override
    public void buildRoof() {
        building.buildRoof("普通屋顶");
    }

    @Override
    public Building getBuilding() {
        return building;
    }
}

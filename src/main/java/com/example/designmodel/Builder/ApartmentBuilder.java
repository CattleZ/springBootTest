package com.example.designmodel.Builder;

/**
 * 多层公寓施工方
 */
public class ApartmentBuilder implements Builder{
    private Building building;
    public ApartmentBuilder()
    {
        building = new Building();
    }

    @Override
    public void buildBasic() {
        building.buildBasic("多层公寓地基");
    }

    @Override
    public void buildWall() {
        building.buildWall("多层公寓墙");
    }

    @Override
    public void buildRoof() {
        building.buildRoof("多层公寓屋顶");
    }

    @Override
    public Building getBuilding() {
        return building;
    }
}

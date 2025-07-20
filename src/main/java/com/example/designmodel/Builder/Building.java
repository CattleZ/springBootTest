package com.example.designmodel.Builder;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<String> buildingComponents = new ArrayList<>();

    // 建造地基
    public void buildBasic(String  basic)
    {
        buildingComponents.add(basic);
    }
    // 建造墙体
    public void buildWall(String wall)
    {
        buildingComponents.add(wall);
    }
    // 建造 roof
    public void buildRoof(String roof)
    {
        buildingComponents.add(roof);
    }
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String component : buildingComponents)
        {
            stringBuilder.append(component);
        }
        return stringBuilder.toString();
    }
}

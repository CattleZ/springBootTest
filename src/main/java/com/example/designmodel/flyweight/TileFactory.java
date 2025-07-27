package com.example.designmodel.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TileFactory {
    private Map<String, Drawable> images;

    public TileFactory() {
        images = new HashMap<String, Drawable>();
    }

    public Drawable getTile(String imageName) {

        if (!images.containsKey(imageName)) {
            switch (imageName){
                case "grass":
                    images.put(imageName, new Grass(imageName));
                    break;
                case "road":
                    images.put(imageName, new Road(imageName));
                    break;
                case "house":
                    images.put(imageName, new House(imageName));
                    break;
                case "river":
                    images.put(imageName, new River(imageName));
                    break;
                default:
                    break;
            }
        }
        return images.get(imageName);
    }
}

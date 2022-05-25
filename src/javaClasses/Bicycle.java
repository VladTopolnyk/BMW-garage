package javaClasses;

import enums.BicycleType;
import enums.Color;

public class Bicycle extends Vehicle {

    private Color color;
    private BicycleType bicycleType;

    public Bicycle(Color color, BicycleType bicycleType) {
        super(color, bicycleType);
        this.color = color;
        this.bicycleType = bicycleType;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "price=" + bicycleType.getPrice() +
                "$, color=" + color.name() +
                ", maxSpeed=" + bicycleType.getMaxSpeed() +
                ", BicycleType=" + bicycleType.name() +
                '}';
    }
}

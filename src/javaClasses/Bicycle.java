package javaClasses;

import enums.BicycleType;
import enums.Color;

public class Bicycle extends Vehicle {

    public Bicycle(Color color, BicycleType bicycleType) {
        super(color, bicycleType);
        this.color = color;
        this.vehicleType = bicycleType;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bicycle{" + vehicleType.toString() + ", color = " + color + '}';
    }
}

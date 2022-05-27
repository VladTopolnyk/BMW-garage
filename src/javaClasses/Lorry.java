package javaClasses;

import enums.Color;
import enums.LorryType;


public class Lorry extends Vehicle {

    public Lorry(Color color, LorryType lorryType) {
        super(color, lorryType);
        this.color = color;
        this.vehicleType = lorryType;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Lorry{" + vehicleType.toString() + ", color = " + color + '}';
    }
}

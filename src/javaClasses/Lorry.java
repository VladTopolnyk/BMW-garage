package javaClasses;

import enums.Color;
import enums.LorryType;
import enums.VehicleType;
import enums.VehicleTypeStructure;

class Lorry extends Vehicle {

    private Color color;
    private LorryType lorryType;

    public Lorry(Color color, LorryType lorryType) {
        super(color, lorryType);
        this.color = color;
        this.lorryType = lorryType;
    }

    @Override
    public String toString() {
        return "Lorry{" +
                "price=" + lorryType.getPrice() +
                ", lifting="+ lorryType.getLifting() +
                ", color=" + color.name() +
                ", carType=" + lorryType.name() +
                '}';
    }
}

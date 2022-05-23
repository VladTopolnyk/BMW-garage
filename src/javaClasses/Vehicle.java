package javaClasses;

import enums.Color;
import enums.VehicleTypeStructure;

public abstract class Vehicle {
    private Color color;
    private VehicleTypeStructure vehicleType;

    public Vehicle(Color color, VehicleTypeStructure vehicleType){
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public Color getColor() {
        return color;
    }
    public VehicleTypeStructure getVehicleType() {
        return vehicleType;
    }

    public abstract String toString();
}

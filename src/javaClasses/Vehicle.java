package javaClasses;


import enums.Color;
import enums.VehicleType;

public abstract class Vehicle {
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    private VehicleType vehicleType;

    double price;
    double maxSpeed;
    int createYear;
    Color color;



    public double getPrice() {
        return price;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public int getCreateYear() {
        return createYear;
    }

    public Color getColor() {
        return color;
    }


    public abstract boolean equals(Object obj);

    public abstract String toString();
}

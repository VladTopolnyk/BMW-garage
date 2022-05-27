package javaClasses;

import enums.Color;
import enums.VehicleTypeStructure;

import java.util.Objects;

public abstract class Vehicle {
    protected Color color;
    protected VehicleTypeStructure vehicleType;

    public Vehicle(Color color, VehicleTypeStructure vehicleType) {
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public Color getColor() {
        return color;
    }

    public VehicleTypeStructure getVehicleType() {
        return vehicleType;
    }

    public abstract void setColor(Color color);

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return color == vehicle.color && Objects.equals(vehicleType, vehicle.vehicleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, vehicleType);
    }
}

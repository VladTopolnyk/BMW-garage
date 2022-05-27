package javaClasses;


import enums.CarType;
import enums.Color;


public class Car extends Vehicle {
    public Car(Color color, CarType carType) {
        super(color, carType);
        this.color = color;
        this.vehicleType = carType;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" + vehicleType.toString() + ", color = " + color + '}';
    }
}

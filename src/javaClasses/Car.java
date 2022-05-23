package javaClasses;


import enums.CarType;
import enums.Color;


public class Car extends Vehicle {

    private Color color;
    private CarType carType;

    public Car(Color color, CarType carType) {
        super(color, carType);
        this.carType = carType;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + carType.getPrice() +
                ", color=" + color.name() +
                ", carType=" + carType.name() +
                '}';
    }
}

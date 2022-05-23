package javaClasses;


import enums.CarType;

public class Car extends Vehicle {
    private CarType carType;

    public CarType getCarType() {
        return carType;
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car car = (Car) obj;
        return car.carType == this.carType;
    }

    @Override
    public String toString() {
        return null;
    }


}

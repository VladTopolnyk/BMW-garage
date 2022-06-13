package javaClasses;

import enums.*;

import java.util.Arrays;

import static com.company.Main.inputEnumType;
//Singleton
public class VehicleFactory {
    private VehicleFactory(){}
    public static Vehicle createVehicle(VehicleType vehicleType) {
        Vehicle vehicle = null;
        switch (vehicleType) {
            case CAR -> {
                CarType carType = inputEnumType("Input CAR type-->", CarType.values());
                System.out.println("Colors: " + Arrays.toString(Color.values()));
                Color color = inputEnumType("Input color-->", Color.values());
                vehicle = new Car(color, carType);
            }
            case LORRY -> {
                LorryType lorryType = inputEnumType("Input LORRY type-->", LorryType.values());
                System.out.println("Colors: " + Arrays.toString(Color.values()));
                Color color = inputEnumType("Input color-->", Color.values());
                vehicle = new Lorry(color, lorryType);
            }
            case BICYCLE -> {
                BicycleType bicycleType = inputEnumType("Input BICYCLE type-->", BicycleType.values());
                System.out.println("Colors: " + Arrays.toString(Color.values()));
                Color color = inputEnumType("Input color-->", Color.values());
                vehicle = new Bicycle(color, bicycleType);
            }
        }
        return vehicle;
    }
}

package javaClasses;

import enums.BicycleType;
import enums.CarType;
import enums.LorryType;
import enums.VehicleType;

import java.util.Arrays;

//Singleton
public class Producer {
    private Producer() {
    }

    private static Producer instance;

    public static Producer getInstance() {
        if (instance == null) instance = new Producer();
        return instance;
    }

    private String showCarTypes() {
        return Arrays.toString(CarType.values());
    }

    private String showLorryTypes() {
        return Arrays.toString(LorryType.values());
    }

    private String showBicycleTypes() {
        return Arrays.toString(BicycleType.values());
    }

    public String showTypesToSell(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> showCarTypes();
            case LORRY -> showLorryTypes();
            case BICYCLE -> showBicycleTypes();
        };
    }
}

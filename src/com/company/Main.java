package com.company;

import enums.CarType;
import enums.VehicleType;

import javaClasses.Car;
import javaClasses.Garage;
import javaClasses.Vehicle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static enums.CarType.*;
import static enums.LorryType.*;

public class Main {

    public static void main(String[] args) {
    }
    private static VehicleType InputVehicle() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input vehicle type-->");
            String vehicleType = sc.nextLine();
            for (var type : VehicleType.values()) {
                if (vehicleType.equals(type.toString())) return type;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input!");
            InputVehicle();
        }
        return VehicleType.CAR;
    }

    private static CarType InputCarType() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input car type-->");
            String vehicleType = sc.nextLine();
            for (var type : CarType.values()) {
                if (vehicleType.equals(type.toString())) return type;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input!");
            InputCarType();
        }
        return CarType.COUPE;
    }


}

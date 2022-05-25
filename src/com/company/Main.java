package com.company;

import enums.*;
import javaClasses.*;

import javax.naming.LimitExceededException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class Main {
    private static final int TIME_LIMIT = 30_000;
    private static int CURRENT_TIME = 0;

    public static void main(String[] args) {
        System.out.println(introduction());
        Garage garage = Garage.getInstance();
        System.out.println("Repeating-> Your purpose is to increase your bank!\n\n".toUpperCase(Locale.ROOT) +
                "Now you have your start bank = " + garage.getBank() +
                "$, And " + garage.getFree_places() + " free places for vehicles to stay!\n" +
                "To sell vehicles you have to buy it from Producer!\nGo to Producer...\n" +
                "The more vehicles, the more customers for whom you don’t have vehicles, or who came without specific variant" +
                " will have options for buying\n");
        Producer producer = Producer.getInstance();
        VehicleFactory vehicleFactory = new VehicleFactory();
        buyVehicle(garage, producer, vehicleFactory);

        System.out.println(garage.getVehicles());
        while (true) {
            if (waitForConsumer()) {
                timeIsOver(garage);
                break;
            } else {
                Consumer consumer = new Consumer("Consumer", generateVehicle());
                System.out.println(consumer.getName());
                if (consumer.getVehicle() == null) {
                    consumer.setCash(generateCash());
                    garage.sellVehicleNullCase(consumer);
                } else {
                    garage.sellVehicleNotNullCase(consumer);
                }
                System.out.println("Now your profit is " + garage.getProfit() + "$\n");
                changeTimeToVisit(consumer.getDiscount(), garage);
                System.out.println(Math.round(TIME_LIMIT / 1000 - CURRENT_TIME * -1) + " seconds left");
                System.out.println("Next consumer will come in " + Garage.getTimeToVisit() + "ms...");
            }
        }
    }

    private static void timeIsOver(Garage garage) {
        System.out.println("Time HAS GONE!");
        if (garage.getBank() > 500_000) {
            System.out.println("Your profit is " + (garage.getBank() - 500_000) + "$\nNice work body ;)");
        } else if (garage.getBank() == 500_000) {
            System.out.println("Your profit is 0$\nYou should think better to have profit in next time!\nSee you :)");
        } else {
            System.out.println("You LOST " + (500_000 - garage.getBank()) + "$\n" +
                    "You should think better to have profit in next time!\nSee you :)");
        }
    }

    public static int inputDiscount() {
        try {
            System.out.print("Input discount-->");
            Scanner sc = new Scanner(System.in);
            int discount = sc.nextInt();
            sc.nextLine();
            if (discount < 1 || discount > 10) throw new IllegalArgumentException();
            return discount;
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input! The highest discount is 10% and the lowest is 1%!");
            return inputDiscount();
        }
    }

    private static int generateCash() {
        return new Random().nextInt(110_000 - 20_000) + 20_000;
    }


    private static void changeTimeToVisit(int discount, Garage garage) {
        switch (discount) {
            case 1 -> garage.setTimeToVisit(garage.getTimeToVisit() - 50);
            case 2 -> garage.setTimeToVisit(garage.getTimeToVisit() - 100);
            case 3 -> garage.setTimeToVisit(garage.getTimeToVisit() - 150);
            case 4 -> garage.setTimeToVisit(garage.getTimeToVisit() - 200);
            case 5 -> garage.setTimeToVisit(garage.getTimeToVisit() - 250);
            case 6 -> garage.setTimeToVisit(garage.getTimeToVisit() - 300);
            case 7 -> garage.setTimeToVisit(garage.getTimeToVisit() - 350);
            case 8 -> garage.setTimeToVisit(garage.getTimeToVisit() - 400);
            case 9 -> garage.setTimeToVisit(garage.getTimeToVisit() - 450);
            case 10 -> garage.setTimeToVisit(garage.getTimeToVisit() - 480);
        }
    }

    private static Vehicle generateVehicle() {
        int random_vehicle = 1 + (int) (Math.random() * 5);
        Color color = generateColor();
        Vehicle vehicle = switch (random_vehicle) {
            case 1 -> generateCar(color);
            case 2 -> generateBicycle(color);
            case 3 -> generateLorry(color);
            default -> null;
        };
        return vehicle;
    }

    private static Lorry generateLorry(Color color) {
        int random_lorry = 1 + (int) (Math.random() * 4);
        LorryType lorryType = switch (random_lorry) {
            case 1 -> LorryType.HIGH;
            case 2 -> LorryType.MEDIUM;
            default -> LorryType.LOW;
        };
        return new Lorry(color, lorryType);
    }

    private static Color generateColor() {
        int random_color = 1 + (int) (Math.random() * 6);
        return switch (random_color) {
            case 1 -> Color.BLUE;
            case 2 -> Color.DARK;
            case 3 -> Color.GREY;
            case 4 -> Color.PINK;
            default -> Color.RED;
        };
    }

    private static Car generateCar(Color color) {
        int random_car = 1 + (int) (Math.random() * 7);
        CarType carType = switch (random_car) {
            case 1 -> CarType.COUPE;
            case 2 -> CarType.CROSSOVER;
            case 3 -> CarType.HATCHBACK;
            case 4 -> CarType.JEEP;
            case 5 -> CarType.SEDAN;
            default -> CarType.UNIVERSAL;
        };
        return new Car(color, carType);
    }

    private static Bicycle generateBicycle(Color color) {
        int random_bicycle = 1 + (int) (Math.random() * 4);
        BicycleType bicycleType = switch (random_bicycle) {
            case 1 -> BicycleType.BMX;
            case 2 -> BicycleType.MOUNTAIN_BIKE;
            default -> BicycleType.ROAD_BIKE;
        };
        return new Bicycle(color, bicycleType);
    }

    private static boolean waitForConsumer() {
        System.out.println("Now you can sell your vehicles!");
        System.out.println("You should wait for the consumer!!!\nWaiting for consumer...\n");
        try {
            Thread.sleep(Garage.getTimeToVisit());
            CURRENT_TIME += Garage.getTimeToVisit();
            if (CURRENT_TIME > TIME_LIMIT) throw new LimitExceededException();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (LimitExceededException e) {
            return true;
        }
    }

    private static int buyVehicle(Garage garage, Producer producer, VehicleFactory vehicleFactory) {

        System.out.println("Your bank is " + garage.getBank());

        System.out.println("Your bank is " + garage.getBank() + '$');

        System.out.println("There are " + garage.getFree_places() + " free places");
        System.out.println("What type of Vehicle do you want to buy?");
        System.out.println("Vehicle Types: " + Arrays.toString(VehicleType.values()));
        VehicleType vehicleType = inputEnumType("Input vehicle type-->", VehicleType.values());
        System.out.println(producer.showTypesToSell(vehicleType));
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleType);
        if (!garage.buyVehicle(vehicle)) System.out.println("You did not buy a place, so you can not buy a vehicle :(");
        if (garage.getFree_places() == garage.getPlaces()) buyVehicle(garage, producer, vehicleFactory);
        System.out.println("To buy another vehicles press ENTER\nOtherwise press another button!");
        if (!pressEnter()) return garage.getPlaces() - garage.getFree_places();
        return buyVehicle(garage, producer, vehicleFactory);
    }

    public static boolean pressEnter() {
        Scanner sc = new Scanner(System.in);
        String key = "";
        return key.equals(sc.nextLine());
    }

    private static String introduction() {
        return "Hello dude ;)\n" +
                "This app is to understand how the garage works for real!\n" +
                "Who are you?\n" +
                "You are the owner of the BMW-garage!\n" +
                "Your purpose is to earn as much as possible in 30 seconds!\n" +
                "Time is counting only when you are waiting for consumer!\nSo you should`t rush :)\n" +
                "Buy and sell vehicles thinking about your future profit!\n" +
                "Think you are ready! Good luck :)\n" +
                "Let`s begin!\n";
    }

    public static <T extends Enum<T>> T inputEnumType(String message, T[] values) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print(message);
            String InputVehicleType = sc.nextLine().toUpperCase(Locale.ROOT).trim();
            for (var type : values) {
                if (InputVehicleType.equals(type.name())) return type;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input!");
            return inputEnumType(message, values);
        }
    }


}

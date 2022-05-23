package javaClasses;

public class Consumer {
    private int cash;
    private Vehicle vehicle;
    private String name;
    public static int id = 0;
    private static int TIME_TO_VISIT = 5_000;

    public Consumer(String name, Vehicle vehicle) {
        id++;
        this.vehicle = vehicle;
        this.name = name;
    }

    public static int getTimeToVisit() {
        return TIME_TO_VISIT;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public static int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

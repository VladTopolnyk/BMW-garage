package javaClasses;

public class Consumer {
    private int cash;
    private Vehicle vehicle;
    private String name;
    private int discount;
    private static int id = 0;

    public Consumer(String name, Vehicle vehicle) {
        id++;
        this.vehicle = vehicle;
        this.name = name + id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setCash(int cash) {
        this.cash = cash;
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

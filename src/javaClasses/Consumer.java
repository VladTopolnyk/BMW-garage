package javaClasses;

public class Consumer {
    private double cash;
    private String name;
    private static int id = 0;

    public Consumer(double cash, String name) {
        id++;
        this.cash = cash;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public static int getId() {
        return id;
    }
}

package javaClasses;

import java.awt.event.KeyEvent;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//Singleton
public class Garage {
    private final int PLACE_PRICE = 10_000;
    private List<Vehicle> vehicles;
    private int bank;
    private int places;
    private int free_places;
    private int profit;

    private Garage() {
        vehicles = new LinkedList();
        bank = 500_000;
        places = 6;
        free_places = 6;
        profit = 0;
    }

    private static Garage instance;

    public static Garage getInstance() {
        if (instance == null) instance = new Garage();
        return instance;
    }

    public int getBank() {
        return bank;
    }

    public int getPlaces() {
        return places;
    }

    public int getFree_places() {
        return free_places;
    }

    public int getProfit() {
        return profit;
    }

    public boolean buyVehicle(Vehicle vehicle) {
        if (bank >= vehicle.getPrice()) {
            if ((free_places - 1) != 0) {
                System.out.println("There are no free places in your garage!");
                System.out.println("To buy a place for the vehicle press ENTER\nOtherwise press another button!");
                pressEnter();
                bank -= vehicle.getPrice();
                vehicles.add(vehicle);
                free_places--;
                return true;
            }
        }
        return false;
    }

    private boolean pressEnter() {
        Scanner sc = new Scanner(System.in);
        String key = "";
        return key.equals(sc.nextLine());
    }

    public int sellVehicle(Vehicle vehicle, int finalPrice, int discount) {
        int maxPossiblePrice = (int) (vehicle.getPrice() + vehicle.getPrice() / 100 * 10);
        if (finalPrice > maxPossiblePrice) throw new IllegalArgumentException();
        if (discount != 0) finalPrice -= finalPrice / 100 * discount;
        bank += finalPrice;
        vehicles.remove(vehicle);
        free_places++;
        profit += finalPrice - vehicle.getPrice();
        return profit;
    }

    public boolean buyPlace() {
        if (bank >= PLACE_PRICE) {
            places++;
            free_places++;
            return true;
        }
        return false;
    }
}

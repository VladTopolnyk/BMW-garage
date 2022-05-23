package javaClasses;

import java.util.*;

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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean buyVehicle(Vehicle vehicle) {
        if (bank >= vehicle.getVehicleType().getPrice()) {
            if ((free_places - 1) != 0) {
                System.out.println("There are no free places in your garage!");
                System.out.println("To buy a place for the vehicle press ENTER\nOtherwise press another button!");
                pressEnter();
                bank -= vehicle.getVehicleType().getPrice();
                vehicles.add(vehicle);
                free_places--;
                return true;
            }
        }
        return false;
    }

    public int sellVehicle(Consumer consumer, int finalPrice, int discount) {
        final String CONSUMER_GONE = "Consumer " + consumer.getName() + "has gone!:(";
        Vehicle vehicle = consumer.getVehicle();
        if (vehicle == null) {
            List<Vehicle> ableVehicles = ableVehicles(consumer);
            if (ableVehicles == null) {
                System.out.println(CONSUMER_GONE);
                return profit;
            }
            System.out.println("Choose the vehicle that you want to sell" +
                    " by inputting the number from 1 to " + ableVehicles.size());
            int index = inputNum(ableVehicles.size()) - 1;
            Vehicle vehicleToSell = ableVehicles.get(index);
            discount = consumer.getCash() / finalPrice * 100;
            hasVehicle(consumer, finalPrice, discount, vehicleToSell);
            return profit;
        }
        if (vehicles.contains(vehicle)) {
            hasVehicle(consumer, finalPrice, discount, vehicle);
            return profit;
        }
        hasNotVehicle(consumer, finalPrice, discount, vehicle);
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


    private boolean pressEnter() {
        Scanner sc = new Scanner(System.in);
        String key = "";
        return key.equals(sc.nextLine());
    }

    private void hasVehicle(Consumer consumer, int finalPrice, int discount, Vehicle vehicle) {
        int maxPossiblePrice = (consumer.getVehicle().getVehicleType().getPrice()
                + consumer.getVehicle().getVehicleType().getPrice() / 100 * 10);
        if (finalPrice > maxPossiblePrice) throw new IllegalArgumentException();
        if (discount != 0) finalPrice -= finalPrice / 100 * discount;
        bank += finalPrice;
        vehicles.remove(vehicle);
        free_places++;
        profit += finalPrice - vehicle.getVehicleType().getPrice();
    }

    private void hasNotVehicle(Consumer consumer, int finalPrice, int discount, Vehicle vehicle) {
        final String CONSUMER_GONE = "Consumer " + consumer.getName() + "has gone!:(";
        System.out.println("You don`t have a vehicle that consumer needs :(");
        if (bank >= vehicle.getVehicleType().getPrice()) {
            System.out.println("But you can buy it!\n");
            System.out.println("To buy a vehicle press ENTER\nOtherwise press another button!");
            if (pressEnter()) {
                hasVehicle(consumer, finalPrice, discount, vehicle);
            } else {
                System.out.println(CONSUMER_GONE);
            }
        } else {
            System.out.println(CONSUMER_GONE);
        }
    }

    private List<Vehicle> ableVehicles(Consumer consumer) {
        Map<Integer, Vehicle> ablePriceList = ablePriceList(consumer.getCash());
        if (ablePriceList.isEmpty()) return null;
        List<Vehicle> vehicleToSell = new ArrayList<>();
        System.out.println("There are vehicles that you can sell to " + consumer.getName() + ":");
        StringBuilder builder = new StringBuilder();
        for (var vehicle : ablePriceList.values()) {
            vehicleToSell.add(vehicle);
            builder.append(vehicleToSell.size() + "-vehicle --" + vehicle.toString() + "--");
            for (var discount : ablePriceList.keySet()) {
                if (!((100 - discount) > 20))
                    builder.append("You should make " + (100 - discount) + "% discount to sell it!");
            }
            builder.append("\n");
        }
        return vehicleToSell;
    }

    private int inputNum(int size) {
        int num = 1;
        try {
            System.out.print("Your answer-->");
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
            sc.nextLine();
            if (num < 1 || num > size) {
                System.out.println("Wrong input");
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            inputNum(size);
        }
        return num;
    }

    private Map<Integer, Vehicle> ablePriceList(int consumerCash) {
        Map<Integer, Vehicle> ablePrices = new TreeMap();
        for (var item : vehicles) {
            int price = item.getVehicleType().getPrice();
            if ((price - (price / 100 * 20)) <= consumerCash) {
                int percent = consumerCash / price * 100;
                ablePrices.put(percent, item);
            }
        }
        return ablePrices;
    }
}

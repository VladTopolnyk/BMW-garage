package javaClasses;

import java.util.*;

import static com.company.Main.*;

//Singleton
public class Garage {
    public static int getTimeToVisit() {
        return TIME_TO_VISIT;
    }

    public static void setTimeToVisit(int timeToVisit) {
        TIME_TO_VISIT = timeToVisit;
    }

    private static int TIME_TO_VISIT = 5_000;
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

    public static String ConsumerGone(Consumer consumer) {
        if (consumer.getCash() == 0) return consumer.getName() +
                " has gone because you don`t have variants for his cash = "
                + consumer.getVehicle().getVehicleType().getPrice() + "$ :(";
        return consumer.getName() + " has gone because you don`t have variants for his cash = " + consumer.getCash() + "$ :(";
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

    public String getVehicles() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var item : vehicles) {
            stringBuilder.append(item + "\n");
        }
        return stringBuilder.toString();
    }

    public boolean buyVehicle(Vehicle vehicle) {
        if (bank >= vehicle.getVehicleType().getPrice()) {
            if ((free_places - 1) <= 0) {
                System.out.println("There are no free places in your garage!");
                System.out.println("To buy a place for the vehicle press ENTER\nOtherwise press another button!");
                if (!pressEnter()) return false;
            }
            bank -= vehicle.getVehicleType().getPrice();
            vehicles.add(vehicle);
            free_places--;
            return true;
        }
        System.out.println("You do not have enough money to buy this vehicle!");
        return false;
    }

    public boolean sellVehicleNotNullCase(Consumer consumer) {
        Vehicle vehicle = consumer.getVehicle();
        if (vehicles.contains(vehicle.getVehicleType())) {
            int finalPrice = formFinalPrice(consumer.getVehicle(), consumer);
            hasVehicle(vehicle, finalPrice);
            return true;
        }
        if (!hasNotVehicle(consumer))
            return false;
        return true;
    }

    public boolean sellVehicleNullCase(Consumer consumer) {
        List<Vehicle> ableVehicles = ableVehicles(consumer);
        if (ableVehicles == null) {
            System.out.println("There are no able vehicles to sell to " + consumer.getName());
            System.out.println(ConsumerGone(consumer));
            return false;
        } else if (ableVehicles.size() == 1) {
            sellVehicle(consumer, ableVehicles.get(0));
            return true;
        }
        System.out.println("Choose the vehicle that you want to sell" +
                " by inputting the number from 1 to " + ableVehicles.size());
        int index = inputIndex(ableVehicles.size()) - 1;
        Vehicle vehicleToSell = ableVehicles.get(index);
        sellVehicle(consumer, vehicleToSell);
        return true;
    }

    private void sellVehicle(Consumer consumer, Vehicle vehicle) {
        int finalPrice;
        if (consumer.getCash() == vehicle.getVehicleType().getPrice()) {
            finalPrice = vehicle.getVehicleType().getPrice();
            hasVehicle(vehicle, finalPrice);
        } else if (consumer.getCash() > vehicle.getVehicleType().getPrice()) {
            finalPrice = formFinalPrice(vehicle, consumer);
            hasVehicle(vehicle, finalPrice);
        } else {
            finalPrice = vehicle.getVehicleType().getPrice();
            int discount = 100 - consumer.getCash() / finalPrice * 100;
            finalPrice -= finalPrice / 100 * discount;
            hasVehicle(vehicle, finalPrice);
        }
    }

    private int formFinalPrice(Vehicle vehicle, Consumer consumer) {
        int discount = 0;
        int finalPrice = finalPrice(vehicle);
        System.out.println("Final price is " + finalPrice + "$\nNow you can set the discount!\n" +
                "The larger the discount, the more often buyers will come," +
                " but you will earn less on the sale\nIf you want to set discount press ENTER!\nOtherwise press another button");
        if (pressEnter()) {
            discount = inputDiscount();
            finalPrice -= finalPrice / 100 * discount;
        }
        consumer.setDiscount(discount);
        return finalPrice;
    }

    private int finalPrice(Vehicle vehicle) {
        return (vehicle.getVehicleType().getPrice() + (vehicle.getVehicleType().getPrice() / 100 * 10));
    }

    private boolean buyPlace() {
        if (bank >= PLACE_PRICE) {
            places++;
            free_places++;
            return true;
        }
        return false;
    }

    private void hasVehicle(Vehicle vehicle, int finalPrice) {
        bank += finalPrice;
        vehicles.remove(vehicle);
        free_places++;
        profit += finalPrice - vehicle.getVehicleType().getPrice();
        System.out.println("You sold " + vehicle);
        System.out.println("Your profit from this deal is " + (finalPrice - vehicle.getVehicleType().getPrice()) + "$");
    }

    private boolean hasNotVehicle(Consumer consumer) {
        Vehicle vehicle = consumer.getVehicle();
        System.out.println("Consumer needs " + consumer.getVehicle());
        System.out.println("You don`t have this one :(\nBut you can buy it!");
        if (bank >= vehicle.getVehicleType().getPrice()) {
            if (free_places <= 0) {
                if (bank < (vehicle.getVehicleType().getPrice() + PLACE_PRICE)) {
                    System.out.println("There are no free places!\n(Vehicle price + place price) = "
                            + (vehicle.getVehicleType().getPrice() + PLACE_PRICE) +
                            "\nBut your bank is " + bank + "$\nSo you do not have enough money to buy a place and a vehicle!");
                    System.out.println(ConsumerGone(consumer));
                    return false;
                }
                System.out.println("There are no free places\nTo buy a vehicle you should buy a place!!!\n" +
                        "press ENTER to buy a place\n" +
                        "Otherwise press another button but consumer would gone!!!");
                if (!pressEnter()) {
                    System.out.println(ConsumerGone(consumer));
                    return false;
                }
                buyPlace();
            }
            System.out.println("Your bank is " + bank + "$");
            System.out.println("Vehicle price is " + vehicle.getVehicleType().getPrice() + "$");
            System.out.println("To buy a vehicle press ENTER\nOtherwise press another button!");
            if (pressEnter()) {
                System.out.println("You bought " + vehicle);
                buyVehicle(vehicle);
                int finalPrice = formFinalPrice(vehicle, consumer);
                hasVehicle(vehicle, finalPrice);
                return true;
            }
        }
        System.out.println(ConsumerGone(consumer));
        return false;
    }

    private List<Vehicle> ableVehicles(Consumer consumer) {
        Map<Integer, Vehicle> ablePriceList = ablePriceList(consumer.getCash());
        if (ablePriceList.isEmpty()) return null;
        List<Vehicle> vehicleToSell = new ArrayList<>();
        System.out.println("There are vehicles that you can sell to " + consumer.getName() + ":");
        for (var vehicle : ablePriceList.values()) {
            vehicleToSell.add(vehicle);
        }
        System.out.println(AbleVehiclesToString(ablePriceList));
        return vehicleToSell;
    }

    private String AbleVehiclesToString(Map<Integer, Vehicle> ablePriceList) {
        StringBuilder builder = new StringBuilder();
        ArrayList<Integer> percents = new ArrayList<>(ablePriceList.keySet());
        int iterator = 1;
        for (var vehicle : ablePriceList.values()) {
            builder.append(iterator + "-" + vehicle.toString() + " -->");
            if ((100 - percents.get(0)) > 0)
                builder.append("You should make " + (100 - percents.get(0)) + "% discount to sell it!");
            else builder.append("You should not make discount to sell it!");
            percents.remove(0);
            builder.append("\n");
            iterator++;
        }
        return builder.toString();
    }

    private int inputIndex(int size) {
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
            inputIndex(size);
        }
        return num;
    }

    private Map<Integer, Vehicle> ablePriceList(int consumerCash) {
        Map<Integer, Vehicle> ablePrices = new TreeMap();
        for (var item : vehicles) {
            int price = item.getVehicleType().getPrice();
            if ((price - (price / 100 * 10)) <= consumerCash) {
                int percent = consumerCash / price * 100;
                ablePrices.put(percent, item);
            }
        }
        return ablePrices;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "vehicles=" + vehicles +
                ", bank=" + bank +
                ", places=" + places +
                ", free_places=" + free_places +
                ", profit=" + profit +
                '}';
    }
}


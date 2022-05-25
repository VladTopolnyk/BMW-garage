package enums;


public enum CarType implements VehicleTypeStructure {
    JEEP(50_000), SEDAN(30_000), CROSSOVER(40_000),
    HATCHBACK(20_000), COUPE(25_000), UNIVERSAL(34_000);

    private int price;

    CarType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name() + "--> price = " + price + '$';
    }
}

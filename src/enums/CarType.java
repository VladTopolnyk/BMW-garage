package enums;


public enum CarType implements VehicleTypeStructure {
    JEEP(50_000), SEDAN(30_000), CROSSOVER(40_000),
    HATCHBACK(20_000), COUPE(25_000), UNIVERSAL(34_000);

    private int price;

    CarType(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String CAR_TYPE = "CarType:";
        return CAR_TYPE + String.format("\n%s -> price = %d$".repeat(6),
                JEEP.name(), JEEP.getPrice(), SEDAN.name(), SEDAN.getPrice(), CROSSOVER.name(), CROSSOVER.getPrice(),
                HATCHBACK.name(), HATCHBACK.getPrice(), COUPE.name(), COUPE.getPrice(), UNIVERSAL.name(), UNIVERSAL.getPrice());
    }
}

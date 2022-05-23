package enums;

public enum BicycleType implements VehicleTypeStructure {
    ROAD_BIKE(50, 1_500), MOUNTAIN_BIKE(35, 1_000), BMX(20, 500);

    private int maxSpeed;
    private int price;

    BicycleType(int maxSpeed, int price) {
        this.maxSpeed = maxSpeed;
        this.price = price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String BICYCLE_TYPE = "BicycleType:";
        return BICYCLE_TYPE + String.format("\n%s -> maxSpeed = %d, price = %d$".repeat(3),
                ROAD_BIKE.name(), ROAD_BIKE.getMaxSpeed(), ROAD_BIKE.getPrice(),
                MOUNTAIN_BIKE.name(), MOUNTAIN_BIKE.getMaxSpeed(), MOUNTAIN_BIKE.getPrice(),
                BMX.name(), BMX.getMaxSpeed(), BMX.getPrice());
    }
}

package enums;

public enum LorryType implements VehicleTypeStructure  {
    HIGH(30, 100_000), MEDIUM(10, 60_000), LOW(4, 37_000);

    private int lifting;
    private int price;

    LorryType(int lifting, int price) {
        this.lifting = lifting;
        this.price = price;
    }

    public int getLifting() {
        return lifting;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String LORRY_TYPE = "Lorry types:";
        return LORRY_TYPE + String.format("\n%s -> maxLifting = %d ton, price = %d$".repeat(3),
                HIGH.name(), HIGH.getLifting(), HIGH.getPrice(), MEDIUM.name(), MEDIUM.getLifting(), MEDIUM.getPrice(),
                LOW.name(), LOW.getLifting(), LOW.getPrice());
    }
}

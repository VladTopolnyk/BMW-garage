package enums;

public enum LorryType implements VehicleTypeStructure {
    HIGH(30, 100_000), MEDIUM(10, 60_000), LOW(4, 37_000);

    private int lifting;
    private int price;

    LorryType(int lifting, int price) {
        this.lifting = lifting;
        this.price = price;
    }


    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name() +
                "--> lifting = " + lifting + " ton" +
                ", price = " + price + '$';
    }
}

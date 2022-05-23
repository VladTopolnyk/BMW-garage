package enums;

public enum VehicleType {
    CAR, BICYCLE, LORRY;

    @Override
    public String toString() {
        return String.format("Vehicle Types: " + CAR.name() + BICYCLE.name() + LORRY.name());
    }
}

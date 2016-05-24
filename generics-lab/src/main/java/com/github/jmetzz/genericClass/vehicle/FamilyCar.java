package com.github.jmetzz.genericClass.vehicle;

import static com.google.common.base.Preconditions.checkArgument;

public class FamilyCar extends MotorVehicle implements PassengerVehicle{

    private static final int DEFAULT_NUM_SEATS = 4;

    private int numberOfSeats = DEFAULT_NUM_SEATS;

    public FamilyCar(String model, String brand,int power) {
        this(model, brand, power, FuelType.FLEX);
    }

    public FamilyCar(String model, String brand, int power, FuelType fuelType) {
        super(VehicleCategory.MOTORCYCLE, model, brand, power);
        super.setFuelType(fuelType);
    }

    @Override
    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }

    @Override
    public void setNumberOfSeats(int numberOfSeats) {
        checkArgument(numberOfSeats > 0, "Every PassengerVehicle should have seats" );
        checkArgument(numberOfSeats < 8, "Family cars don't support more than 7 passengers" );
        this.numberOfSeats = numberOfSeats;
    }
}

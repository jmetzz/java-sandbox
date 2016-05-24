package com.github.jmetzz.genericClass.vehicle;


import static com.google.common.base.Preconditions.checkArgument;

public class Motorcycle extends MotorVehicle implements PassengerVehicle{

    private static final int DEFAULT_NUM_SEATS = 2;

    private int numberOfSeats = DEFAULT_NUM_SEATS;

    public Motorcycle(String model, String brand,int power) {
        this(model, brand, power, FuelType.FLEX);
    }

    public Motorcycle(String model, String brand, int power, FuelType fuelType) {
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
        checkArgument(numberOfSeats < 3, "Motorcycles don't support more than 2 passengers" );
        this.numberOfSeats = numberOfSeats;
    }
}

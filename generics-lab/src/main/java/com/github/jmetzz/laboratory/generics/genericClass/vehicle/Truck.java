package com.github.jmetzz.laboratory.generics.genericClass.vehicle;

public class Truck extends MotorVehicle implements UtilityVehicle {

    private int capacity;

    private int length;

    public Truck(String model, String brand, int power) {
        this(model, brand, power, FuelType.DIESEL);
    }

    public Truck(String model, String brand, int power, FuelType fuelType) {
        super(VehicleCategory.CARGO, model, brand, power);
        super.setFuelType(fuelType);
    }


    @Override
    public int capacityVolume() {
        return this.capacity;
    }


    @Override
    public int length() {
        return this.length;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public void setLength(int length) {
        this.length = length;
    }
}

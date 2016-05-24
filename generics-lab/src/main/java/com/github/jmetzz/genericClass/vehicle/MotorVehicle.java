package com.github.jmetzz.genericClass.vehicle;

import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class MotorVehicle extends BaseVehicle implements EnginePropulsionVehicle{

    private static final FuelType DEFAULT_FUEL_TYPE = FuelType.FLEX;
    private static final int DEFAULT_NUM_WHEELS = 4;
    private static final GearBox DEFAULT_GEAR_BOX = new GearBox(6, "Porsche");

    private FuelType fuelType;
    private int fuelCapacity;
    private int fuelAvailable;

    private int hp;
    private int wheels;
    private GearBox gearBox;


    public MotorVehicle(VehicleCategory category, String model, String vendor, int power) {
        super(category, model, vendor);
        setPower(power);
        this.fuelType = DEFAULT_FUEL_TYPE;
        this.wheels = DEFAULT_NUM_WHEELS;
        this.gearBox = DEFAULT_GEAR_BOX;
    }

    @Override
    public void setPower(int hp) {
        checkArgument(hp > 0, "Engine power must be a positive value. Given " + hp);
        this.hp = hp;
    }

    @Override
    public int getPower() {
        return hp;
    }

    @Override
    public GearBox gearBox() {
        return this.gearBox;
    }

    @Override
    public void setGearBox(GearBox box) {
        this.gearBox = box;
    }

    @Override
    public int getWheels() {
        return this.wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }


    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelCapacity(int capacity){
        checkArgument(capacity > 0, "Fuel tank should store more then 0 liters");
        this.fuelCapacity = capacity;
    };


    public int getFuelCapacity(){
        return this.fuelCapacity;
    };

    public int getAvailableFuel(){
        return this.fuelAvailable;
    };


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fuelType", fuelType)
                .add("hp", hp)
                .add("wheels", wheels)
                .add("gearBox", gearBox)
                .toString();
    }
}

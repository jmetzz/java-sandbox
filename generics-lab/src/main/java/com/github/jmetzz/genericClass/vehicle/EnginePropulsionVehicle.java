package com.github.jmetzz.genericClass.vehicle;


public interface EnginePropulsionVehicle extends Vehicle {

    void setPower(int hp);
    int getPower();
    GearBox gearBox();
    void setGearBox(GearBox box);
    void setFuelCapacity(int capacity);
    int getFuelCapacity();
    int getAvailableFuel();


}

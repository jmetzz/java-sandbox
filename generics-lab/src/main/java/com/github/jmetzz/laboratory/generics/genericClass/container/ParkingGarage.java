package com.github.jmetzz.laboratory.generics.genericClass.container;

import com.github.jmetzz.laboratory.generics.genericClass.vehicle.MotorVehicle;
import com.github.jmetzz.laboratory.generics.genericClass.vehicle.PassengerVehicle;
import com.github.jmetzz.laboratory.generics.genericClass.vehicle.Vehicle;

public class ParkingGarage<T extends MotorVehicle & Vehicle & PassengerVehicle> {

}

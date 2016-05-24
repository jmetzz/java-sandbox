package com.github.jmetzz.genericClass.container;

import com.github.jmetzz.genericClass.vehicle.MotorVehicle;
import com.github.jmetzz.genericClass.vehicle.PassengerVehicle;
import com.github.jmetzz.genericClass.vehicle.Vehicle;

public class ParkingGarage<T extends MotorVehicle & Vehicle & PassengerVehicle> {

}

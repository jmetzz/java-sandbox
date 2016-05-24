package com.github.jmetzz.genericClass.demoApps;

import com.github.jmetzz.genericClass.vehicle.Vehicle;

import java.util.List;

public class WildcardsDemo {


    <T extends Vehicle> T getFastest(List<T> list) {
        throw new UnsupportedOperationException("TODO");
    }


    int totalFuel(List<? extends Vehicle> list) {
        throw new UnsupportedOperationException("TODO");
    }


    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }


}

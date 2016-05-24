package com.github.jmetzz.genericClass.vehicle;


import com.google.common.base.MoreObjects;

public class GearBox {

    private final int numGears;
    private final String vendor;

    public GearBox(int numGears, String vendor){
        this.numGears = numGears;
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("numGears", numGears)
                .add("vendor", vendor)
                .toString();
    }
}

package com.github.jmetzz.genericClass.vehicle;


import com.google.common.base.MoreObjects;

public abstract class BaseVehicle implements Vehicle{

    private final VehicleCategory category;
    private final String model;
    private final String vendor;


    public BaseVehicle(VehicleCategory category, String model, String vendor) {
        this.category = category;
        this.model = model;
        this.vendor = vendor;
    }


    @Override
    public VehicleCategory category() {
        return this.category;
    }


    @Override
    public String vendor() {
        return this.vendor;
    }

    @Override
    public String model() {
        return this.model;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("category", category)
                .add("model", model)
                .add("vendor", vendor)
                .toString();
    }
}

package com.github.jmetzz.laboratory.generics.genericClass.vehicle;


import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;

public class Cabriolet extends MotorVehicle implements Convertible {


    private static final Logger logger = Logger.getLogger(Cabriolet.class.getName());

    private long foldingTime;

    private HoodStatus status;

    public Cabriolet(String model, String brand,int power, long foldingTime) {
        this(model, brand, power, foldingTime,  FuelType.FLEX);
    }

    public Cabriolet(String model, String brand, int power, long foldingTime, FuelType fuelType) {
        super(VehicleCategory.MOTORCYCLE, model, brand, power);
        super.setFuelType(fuelType);
        this.status = HoodStatus.FOLDED;
        setFoldingTime(foldingTime);
    }

    @Override
    public long timeToFold() {
        return foldingTime;
    }

    @Override
    public void foldHood() throws InterruptedException {

        if (status.equals(HoodStatus.UNFOLDED)) {
            logger.info("Folding ....");
            Thread.sleep(foldingTime);
            logger.info("Folding complete");
        } else {
            logger.info("Hood already folded");
        }

    }

    @Override
    public void unfoldHood() throws InterruptedException {
        if (status.equals(HoodStatus.FOLDED)) {
            logger.info("Unfolding ....");
            Thread.sleep(foldingTime);
            logger.info("Unfolding complete");
        } else {
            logger.info("Hood already unfolded");
        }
    }

    public void setFoldingTime(long foldingTime) {
        checkArgument(foldingTime > 0);
        this.foldingTime = foldingTime;
    }

}

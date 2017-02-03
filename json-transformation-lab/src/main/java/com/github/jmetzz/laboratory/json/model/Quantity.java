package com.github.jmetzz.laboratory.json.model;

import java.math.BigDecimal;

public class Quantity {
    private BigDecimal quantity;
    private String unitCode;

    public Quantity() {
    }

    public Quantity(BigDecimal quantity, String unitCode) {
        this.quantity = quantity;
        this.unitCode = unitCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}

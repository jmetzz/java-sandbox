package com.github.jmetzz.laboratory.json.model;

import java.math.BigDecimal;

public class UnitPrice {
    private BigDecimal amount;
    private String unitCode;

    public UnitPrice() {
    }

    public UnitPrice(BigDecimal amount, String unitCode) {
        this.amount = amount;
        this.unitCode = unitCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}

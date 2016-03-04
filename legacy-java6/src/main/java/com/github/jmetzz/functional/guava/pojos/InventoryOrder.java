package com.github.jmetzz.functional.guava.pojos;

public class InventoryOrder {

    private int id;

    private InventoryOrderState inventoryOrderState;


    public InventoryOrder() {
    }

    public InventoryOrder(InventoryOrderState state) {
        this.inventoryOrderState = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InventoryOrderState getInventoryOrderState() {
        return inventoryOrderState;
    }

    public void setInventoryOrderState(InventoryOrderState inventoryOrderState) {
        this.inventoryOrderState = inventoryOrderState;
    }
}

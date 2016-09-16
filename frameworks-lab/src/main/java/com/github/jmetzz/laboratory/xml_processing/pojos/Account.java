package com.github.jmetzz.laboratory.xml_processing.pojos;

import com.google.common.base.MoreObjects;

import java.io.Serializable;


public class Account implements Serializable {

    static final long serialVersionUID = 7968446023634800013L;
    private String id;
    private String description;
    private boolean isActive;
    private PaymentSystem system;

    public Account() {
    }

    public Account(String id, String description, boolean isActive, PaymentSystem system) {
        this.id = id;
        this.description = description;
        this.isActive = isActive;
        this.system = system;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PaymentSystem getSystem() {
        return system;
    }

    public void setSystem(PaymentSystem system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("isActive", isActive)
                .add("system", system)
                .add("active", isActive())
                .omitNullValues()
                .toString();
    }
}

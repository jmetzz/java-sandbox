package com.github.jmetzz.laboratory.mockito.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jean on 6/02/2017.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String address;

    public Customer() {

    }

    public Customer(long id, String name, String address) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

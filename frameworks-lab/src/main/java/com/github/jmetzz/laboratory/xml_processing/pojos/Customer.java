package com.github.jmetzz.laboratory.xml_processing.pojos;


import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = -5554071826211835857L;

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    public Customer() {}

    public Customer(long id, String fName, String lName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

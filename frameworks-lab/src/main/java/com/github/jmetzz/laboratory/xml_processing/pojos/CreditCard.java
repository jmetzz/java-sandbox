package com.github.jmetzz.laboratory.xml_processing.pojos;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard {

    @XmlAttribute
    private String number;

    @XmlElement(name = "expiry_date")
    private String expiryDate;

    @XmlElement(name = "control_number")
    private Integer controlNumber;

    private String type;

    public CreditCard(){}

    public CreditCard(String number, String expiryDate, Integer controlNumber, String type) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.controlNumber = controlNumber;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .add("expiryDate", expiryDate)
                .add("controlNumber", controlNumber)
                .add("type", type)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equal(number, that.number) &&
                Objects.equal(expiryDate, that.expiryDate) &&
                Objects.equal(controlNumber, that.controlNumber) &&
                Objects.equal(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number, expiryDate, controlNumber, type);
    }
}


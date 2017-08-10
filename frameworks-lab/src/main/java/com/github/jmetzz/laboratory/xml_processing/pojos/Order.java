package com.github.jmetzz.laboratory.xml_processing.pojos;


import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Order implements Serializable {
    static final long serialVersionUID = 7968446023634800000L;

    private String orderID;
    private String dossier;
    private LocalDateTime creationDate;
    private String description;

    private Account account;
    private BigDecimal amount;
    private String currency;
    private String alias;
    private String issuerId;
    private boolean use3DSecurity;

    private Customer customer;

    public Order(String orderID, String dossier, LocalDateTime creationDate, String description, Account account, BigDecimal amount, String currency, String alias, String issuerId, boolean use3DSecurity, Customer customer) {
        this.orderID = orderID;
        this.dossier = dossier;
        this.creationDate = creationDate;
        this.description = description;
        this.account = account;
        this.amount = amount;
        this.currency = currency;
        this.alias = alias;
        this.issuerId = issuerId;
        this.use3DSecurity = use3DSecurity;
        this.customer = customer;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public boolean isUse3DSecurity() {
        return use3DSecurity;
    }

    public void setUse3DSecurity(boolean use3DSecurity) {
        this.use3DSecurity = use3DSecurity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderID", orderID)
                .add("dossier", dossier)
                .add("creationDate", creationDate)
                .add("description", description)
                .add("account", account)
                .add("amount", amount)
                .add("currency", currency)
                .add("alias", alias)
                .add("issuerId", issuerId)
                .add("use3DSecurity", use3DSecurity)
                .add("customer", customer)
                .omitNullValues()
                .toString();
    }
}
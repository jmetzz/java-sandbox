package com.github.jmetzz.laboratory.xml_processing.pojos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderBuilder {
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

    public OrderBuilder withOrderID(String orderID) {
        this.orderID = orderID;
        return this;
    }

    public OrderBuilder withDossier(String dossier) {
        this.dossier = dossier;
        return this;
    }

    public OrderBuilder withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public OrderBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public OrderBuilder withAccount(Account account) {
        this.account = account;
        return this;
    }

    public OrderBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public OrderBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public OrderBuilder withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public OrderBuilder withIssuerId(String issuerId) {
        this.issuerId = issuerId;
        return this;
    }

    public OrderBuilder withUse3DSecurity(boolean use3DSecurity) {
        this.use3DSecurity = use3DSecurity;
        return this;
    }

    public OrderBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Order createOrder() {
        return new Order(orderID, dossier, creationDate, description, account, amount, currency, alias, issuerId, use3DSecurity, customer);
    }
}
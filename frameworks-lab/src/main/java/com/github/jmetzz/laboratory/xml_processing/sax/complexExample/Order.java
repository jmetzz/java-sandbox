package com.github.jmetzz.laboratory.xml_processing.sax.complexExample;


import java.io.Serializable;
import java.util.List;


public abstract class Order implements Serializable {
    static final long serialVersionUID = 7968446023634800000L;

    private String orderID;
    private String dossier;
    private long creationDate;
    private String description;

    private Account account;
    private String amount;
    private String currency;
    private String alias;
    private String issuerId;
    private String use3DSecurity;

    public Customer customer;

//    private List<Products> products;

    public Order(String orderID, String dossier, long creationDate, String description, Account account, String amount, String currency, String alias, String issuerId, String use3DSecurity, Customer customer) {
//    public Order(String orderID, String dossier, long creationDate, String description, Account account, String amount, String currency, String alias, String issuerId, String use3DSecurity, Customer customer, List<Products> products) {
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
//        this.products = products;
    }


    static class OrderBuilder {
        private String orderID;
        private String dossier;
        private long creationDate;
        private String description;

        private Account account;
        private String amount;
        private String currency;
        private String alias;
        private String issuerId;
        private String use3DSecurity;

        public Customer customer;

//        private List<Products> products;


    }
}

package com.github.jmetzz.laboratory.json.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.Future;

/**
 * Created by jean on 3/01/2017.
 */
public class OrderFutureHolder {

    private JsonNode order;
    private Future<JsonNode> seller;
    private Future<JsonNode> customer;
    private Future<JsonNode> customerPreferences;
    private Future<JsonNode> billingAddresses;
    private Future<JsonNode> deliveryAddresses;
    private Future<JsonNode> deliveryPreferences;


    public JsonNode getOrder() {
        return order;
    }

    public void setOrder(JsonNode order) {
        this.order = order;
    }

    public Future<JsonNode> getDeliveryPreferences() {
        return deliveryPreferences;
    }

    public void setDeliveryPreferences(Future<JsonNode> deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }

    public Future<JsonNode> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(Future<JsonNode> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public Future<JsonNode> getSeller() {
        return seller;
    }

    public void setSeller(Future<JsonNode> seller) {
        this.seller = seller;
    }

    public Future<JsonNode> getCustomerPreferences() {
        return customerPreferences;
    }

    public void setCustomerPreferences(Future<JsonNode> customerPreferences) {
        this.customerPreferences = customerPreferences;
    }

    public Future<JsonNode> getCustomer() {
        return customer;
    }

    public void setCustomer(Future<JsonNode> customer) {
        this.customer = customer;
    }


    public Future<JsonNode> getBillingAddresses() {
        return billingAddresses;
    }

    public void setBillingAddresses(Future<JsonNode> billingAddresses) {
        this.billingAddresses = billingAddresses;
    }


    public static OrderFutureBuilder newBuilder() {
        return new OrderFutureBuilder();
    }

    public static OrderFutureBuilder buildUpon(OrderFutureHolder original) {
        OrderFutureBuilder builder = newBuilder();
        builder.order(original.getOrder());
        builder.deliveryPreferences(original.getDeliveryPreferences());
        builder.deliveryAddresses(original.getDeliveryAddresses());
        builder.seller(original.getSeller());
        builder.customerPreferences(original.getCustomerPreferences());
        builder.customer(original.getCustomer());
        builder.billingAddresses(original.getBillingAddresses());
        return builder;
    }

    public static class OrderFutureBuilder {

        private JsonNode order;
        private Future<JsonNode> deliveryPreferences;
        private Future<JsonNode> deliveryAddresses;
        private Future<JsonNode> seller;
        private Future<JsonNode> customerPreferences;
        private Future<JsonNode> customer;
        private Future<JsonNode> billingAddresses;

        public OrderFutureBuilder order(JsonNode value) {
            this.order = value;
            return this;
        }

        public OrderFutureBuilder deliveryPreferences(Future<JsonNode> value) {
            this.deliveryPreferences = value;
            return this;
        }

        public OrderFutureBuilder deliveryAddresses(Future<JsonNode> value) {
            this.deliveryAddresses = value;
            return this;
        }

        public OrderFutureBuilder seller(Future<JsonNode> value) {
            this.seller = value;
            return this;
        }

        public OrderFutureBuilder customerPreferences(Future<JsonNode> value) {
            this.customerPreferences = value;
            return this;
        }

        public OrderFutureBuilder customer(Future<JsonNode> value) {
            this.customer = value;
            return this;
        }

        public OrderFutureBuilder billingAddresses(Future<JsonNode> value) {
            this.billingAddresses = value;
            return this;
        }

        public OrderFutureHolder build() {
            OrderFutureHolder result = new OrderFutureHolder();

            result.setOrder(order);
            result.setDeliveryPreferences(deliveryPreferences);
            result.setDeliveryAddresses(deliveryAddresses);
            result.setSeller(seller);
            result.setCustomerPreferences(customerPreferences);
            result.setCustomer(customer);
            result.setBillingAddresses(billingAddresses);
            return result;
        }
    }

}

package com.github.jmetzz.laboratory.json.model;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * Created by jean on 3/01/2017.
 */
public class OrderMetadata {
    private JsonNode billingAddress;
    private JsonNode customer;
    private JsonNode seller;
    private JsonNode deliveryAddress;
    private JsonNode deliveryInstructions;
    private JsonNode paymentConditions;
    private JsonNode paymentDiscount;

    public JsonNode getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(JsonNode billingAddress) {
        this.billingAddress = billingAddress;
    }

    public JsonNode getCustomer() {
        return customer;
    }

    public void setCustomer(JsonNode customer) {
        this.customer = customer;
    }

    public JsonNode getSeller() {
        return seller;
    }

    public void setSeller(JsonNode seller) {
        this.seller = seller;
    }

    public JsonNode getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(JsonNode deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public JsonNode getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(JsonNode deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public JsonNode getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(JsonNode paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public JsonNode getPaymentDiscount() {
        return paymentDiscount;
    }

    public void setPaymentDiscount(JsonNode paymentDiscount) {
        this.paymentDiscount = paymentDiscount;
    }

    public static OrderMetaDataBuilder newBuilder() {
        return new OrderMetaDataBuilder();
    }

    public static OrderMetaDataBuilder buildFrom(OrderMetadata original) {
        OrderMetaDataBuilder builder = newBuilder();
        builder.billingAddress(original.getBillingAddress());
        builder.customer(original.getCustomer());
        builder.seller(original.getSeller());
        builder.deliveryAddress(original.getDeliveryAddress());
        builder.deliveryInstructions(original.getDeliveryInstructions());
        builder.paymentConditions(original.getPaymentConditions());
        builder.paymentDiscount(original.getPaymentDiscount());

        return builder;
    }

    public static class OrderMetaDataBuilder {
        private JsonNode billingAddress;
        private JsonNode customer;
        private JsonNode seller;
        private JsonNode deliveryAddress;
        private JsonNode deliveryInstructions;
        private JsonNode paymentConditions;
        private JsonNode paymentDiscount;

        public OrderMetaDataBuilder billingAddress(JsonNode value) {
            this.billingAddress = value;
            return this;
        }

        public OrderMetaDataBuilder customer(JsonNode value) {
            this.customer = value;
            return this;
        }


        public OrderMetaDataBuilder seller(JsonNode value) {
            this.seller = value;
            return this;
        }

        public OrderMetaDataBuilder deliveryAddress(JsonNode value) {
            this.deliveryAddress = value;
            return this;
        }

        public OrderMetaDataBuilder deliveryInstructions(JsonNode value) {
            this.deliveryInstructions = value;
            return this;
        }

        public OrderMetaDataBuilder paymentConditions(JsonNode value) {
            this.paymentConditions = value;
            return this;
        }

        public OrderMetaDataBuilder paymentDiscount(JsonNode value) {
            this.paymentDiscount = value;
            return this;
        }

        public OrderMetadata build() {
            OrderMetadata result = new OrderMetadata();
            result.setBillingAddress(billingAddress);
            result.setCustomer(customer);
            result.setSeller(seller);
            result.setDeliveryAddress(deliveryAddress);
            result.setDeliveryInstructions(deliveryInstructions);
            result.setPaymentConditions(paymentConditions);
            result.setPaymentDiscount(paymentDiscount);
            return result;
        }
    }
}

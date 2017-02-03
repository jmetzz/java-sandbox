package com.github.jmetzz.laboratory.json.util;


import com.fasterxml.jackson.databind.JsonNode;

import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.getNodeProperty;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.toJsonString;


/**
 * Created by jean on 3/01/2017.
 */
public class OrderJsonUtils {

    public static String getCustomerId(JsonNode order) {
        return getNodeProperty(getCustomer(order), "customerID").asText(null);
    }

    public static JsonNode getCustomer(JsonNode order) {
        return getNodeProperty(order, "customer");
    }

    public static JsonNode getSeller(JsonNode order) {
        return getNodeProperty(order, "seller");
    }

    public static String getSellerId(JsonNode order) {
        return getNodeProperty(getSeller(order), "sellerID").asText(null);
    }

    public static String getBillingAddressId(JsonNode order) {
        return toJsonString(getNodeProperty(getBillingAddress(order), "billingAddressID"));
    }


    public static JsonNode getBillingAddress(JsonNode order) {
        return getNodeProperty(order, "billingAddress");
    }

    public static JsonNode getBillingContact(JsonNode order) {
        return getNodeProperty(order, "billingContact");
    }

    public static String getBillingContactId(JsonNode order) {
        return getNodeProperty(getBillingContact(order), "billingContactID").asText(null);
    }

    public static JsonNode getTransportType(JsonNode order) {
        return getNodeProperty(order, "transportType");
    }


    public static JsonNode getFuelSurcharge(JsonNode order) {
        return getNodeProperty(order, "fuelSurcharge");
    }


    public static JsonNode getCreatorPerson(JsonNode order) {
        return getNodeProperty(order, "creatorPerson");
    }


    public static JsonNode getDestinationAddress(JsonNode order) {
        return getNodeProperty(order, "destinationAddress");
    }

    public static String getDestinationAddressId(JsonNode order) {
        return getNodeProperty(getDestinationAddress(order), "destinationAddressID").asText(null);
    }


    public static JsonNode getPaymentCondition(JsonNode order) {
        return getNodeProperty(order, "paymentCondition");
    }

    public static JsonNode getPaymentDiscount(JsonNode order) {
        return getNodeProperty(order, "paymentDiscount");
    }

    public static JsonNode getDeliveryInstructions(JsonNode order) {
        return getNodeProperty(order, "deliveryInstructions");
    }
}

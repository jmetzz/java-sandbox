package com.github.jmetzz.laboratory.json.util;

import com.fasterxml.jackson.databind.JsonNode;

import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.getNodeProperty;


public class OrderLineJsonUtils {

    public static JsonNode getUnitPrice(JsonNode orderLine) {
        return getNodeProperty(orderLine, "unitPrice");
    }

    public static JsonNode getSample(JsonNode orderLine) {
        return getNodeProperty(orderLine, "sample");
    }

    public static JsonNode getQuantity(JsonNode orderLine) {
        return getNodeProperty(orderLine, "quantity");
    }


    public static JsonNode getCreatorPerson(JsonNode orderLine) {
        return getNodeProperty(orderLine, "creatorPerson");
    }

    public static JsonNode getProduct(JsonNode orderLine) {
        return getNodeProperty(orderLine, "product");
    }

    public static String getProductId(JsonNode orderLine) {
        return getNodeProperty(getProduct(orderLine), "productID").asText(null);
    }

    public static JsonNode getPackaging(JsonNode orderLine) {
        return getNodeProperty(orderLine, "packaging");
    }

    public static String getPackagingId(JsonNode orderLine) {
        return getNodeProperty(getPackaging(orderLine), "packagingID").asText(null);
    }


}

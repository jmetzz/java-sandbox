package com.github.jmetzz.laboratory.json.service.remote;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by jean on 3/01/2017.
 */
public interface OrderAPI {

    String createOrder(JsonNode order);

    String modifyOrder(String orderId, JsonNode order);

    JsonNode getOrderById(String orderId);

    JsonNode getOrderLinesById(String orderId);

    String addOrderLine(String orderId, JsonNode orderLine);

    String modifyOrderLine(String orderId, String orderLineId, JsonNode orderLine);

    JsonNode getOrderLineById(String orderId, String orderLineId) throws IOException;
}

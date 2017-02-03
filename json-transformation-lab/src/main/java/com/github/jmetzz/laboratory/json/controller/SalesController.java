package com.github.jmetzz.laboratory.json.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jmetzz.laboratory.json.OrderLineMetadataCollector;
import com.github.jmetzz.laboratory.json.OrderMetadataCollector;
import com.github.jmetzz.laboratory.json.model.OrderLineMetadata;
import com.github.jmetzz.laboratory.json.model.OrderMetadata;
import com.github.jmetzz.laboratory.json.service.remote.OrderAPI;
import com.github.jmetzz.laboratory.json.util.OrderJsonUtils;
import com.github.jmetzz.laboratory.json.util.OrderLineJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

/**
 * Created by jean on 3/01/2017.
 */
@RestController
public class SalesController {

    public static final String GET_ORDER_BY_REF_URL = "/sales/order/{orderID}";
    public static final String GET_ORDER_LINE_URL = "/sales/order/{orderID}/line/{orderLineID}";
    public static final String COPY_ORDER_URL = "/sales/order/{orderID}/copy";

    @Autowired
    private OrderAPI orderApi;

    @Autowired
    private OrderMetadataCollector orderMetaDataCollector;

    @Autowired
    private OrderLineMetadataCollector orderLineMetaDataCollector;


    @RequestMapping(value = COPY_ORDER_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> copyOrder(@PathVariable("orderID") String orderId) {
        return copyOrder(orderApi.getOrderById(orderId), orderApi.getOrderLinesById(orderId));
    }


    private ResponseEntity<Void> copyOrder(JsonNode order, JsonNode orderLines) {
        return copyOrder(order, orderLines, orderMetaDataCollector.collect(order));
    }

    private ResponseEntity<Void> copyOrder(JsonNode order, JsonNode orderLines, OrderMetadata metaData) {
        return copyOrderLines(createOrderCopy(order, metaData), orderLines, metaData);
    }

    private String createOrderCopy(JsonNode order, OrderMetadata orderMetaData) {
        return orderApi.createOrder(copyOrder(order, orderMetaData));
    }


    private ResponseEntity<Void> copyOrderLines(String orderId, JsonNode orderLines, OrderMetadata orderMetaData) {
        if (orderLines != null) {
            for (JsonNode orderLine : orderLines) {
                orderApi.addOrderLine(orderId, copyOrderLine(orderLine));
            }
        }
        return new ResponseEntity<>(createLocationHeader(orderId), HttpStatus.CREATED);
    }

    private JsonNode copyOrderLine(JsonNode orderLine) {
        return copyOrderLine(orderLine, orderLineMetaDataCollector.collect(orderLine));
    }

    private HttpHeaders createLocationHeader(String orderId) {
        return createHeaders(GET_ORDER_BY_REF_URL, orderId);
    }


    private HttpHeaders createHeaders(String path, Object... uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(fromPath(path).buildAndExpand(uriVariables).toUri());
        return httpHeaders;
    }


    private ObjectNode copyOrder(JsonNode order, OrderMetadata metaData) {
        ObjectNode orderCopy = JsonNodeFactory.instance.objectNode();
        orderCopy.set("creatorPerson", OrderJsonUtils.getCreatorPerson(order));
        orderCopy.set("fuelSurcharge", OrderJsonUtils.getFuelSurcharge(order));
        orderCopy.set("transportType", OrderJsonUtils.getTransportType(order));
        return setOrderMetadataFrom(metaData, orderCopy);
    }

    private ObjectNode setOrderMetadataFrom(OrderMetadata metaData, ObjectNode destination) {
        destination.set("billingAddress", metaData.getBillingAddress());
        destination.set("customer", metaData.getCustomer());
        destination.set("deliveryAddress", metaData.getDeliveryInstructions());
        destination.set("deliveryInstructions", metaData.getDeliveryInstructions());
        destination.set("paymentCondition", metaData.getPaymentConditions());
        destination.set("paymentDiscount", metaData.getPaymentDiscount());
        destination.set("seller", metaData.getSeller());
        return destination;
    }

    private ObjectNode copyOrderLine(JsonNode orderLine, OrderLineMetadata orderLineMetaData) {
        ObjectNode orderLineCopy = JsonNodeFactory.instance.objectNode();
        orderLineCopy.set("businessCreationDate", getBusinessCreationDate());
        orderLineCopy.set("creatorPerson", OrderLineJsonUtils.getCreatorPerson(orderLine));
        orderLineCopy.set("sample", OrderLineJsonUtils.getSample(orderLine));
        orderLineCopy.set("quantity", OrderLineJsonUtils.getQuantity(orderLine));
        orderLineCopy.set("unitPrice", OrderLineJsonUtils.getUnitPrice(orderLine));

        return setOrderLineMetaDataFrom(orderLineMetaData, orderLineCopy);
    }

    private ObjectNode setOrderLineMetaDataFrom(OrderLineMetadata metadata, ObjectNode destination) {
        destination.set("packaging", metadata.getPackaging());
        destination.set("product", metadata.getProduct());
        return destination;
    }

    private JsonNode getBusinessCreationDate() {
        return JsonNodeFactory.instance.textNode(LocalDate.now().toString());
    }

}

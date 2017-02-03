package com.github.jmetzz.laboratory.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jmetzz.laboratory.json.model.OrderFutureHolder;
import com.github.jmetzz.laboratory.json.model.OrderMetadata;
import com.github.jmetzz.laboratory.json.service.remote.CompanyAPI;
import com.github.jmetzz.laboratory.json.service.remote.CustomerAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Future;

import static com.github.jmetzz.laboratory.json.util.OrderJsonUtils.*;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonTransformer.transform;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.getFutureValue;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.getNodeById;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.getNodeProperty;


/**
 * Created by jean on 3/01/2017.
 */
@Component
public class OrderMetadataCollector {

    @Autowired
    private CustomerAPI customerApi;

    @Autowired
    private CompanyAPI companyApi;

    public OrderMetadata collect(JsonNode order) {
        return OrderMetadata.newBuilder()
                .billingAddress(getNodeProperty(order, "billingAddress"))
                .customer(getNodeProperty(order, "customer"))
                .seller(getNodeProperty(order, "seller"))
                .deliveryInstructions(getNodeProperty(order, "deliveryInstructions"))
                .paymentConditions(getNodeProperty(order, "paymentConditions"))
                .paymentDiscount(getNodeProperty(order, "paymentDiscount"))
                .build();
    }

    public OrderMetadata asynchCollect(JsonNode order) {
        return collect(buildOrderFuture(order));
    }

    private OrderFutureHolder buildOrderFuture(JsonNode order) {
        return OrderFutureHolder.newBuilder()
                .order(order)
                //from company service
                .billingAddresses(getRemoteBillingAddresses(order))
                .deliveryPreferences(getRemoteDeliveryPreferences(order))
                .deliveryAddresses(getRemoteDeliveryAddresses(order))
                //from customer service
                .seller(getRemoteSellers(order))
                .customer(getRemoteCustomer(order))
                .customerPreferences(getRemoteCustomerPreferences(order))
                .build();
    }

    private OrderMetadata collect(OrderFutureHolder future) {
        return OrderMetadata.newBuilder()
                .billingAddress(findBillingAddress(future))
                .deliveryInstructions(findDeliveryInstructions(future))
                .customer(findCustomer(future))
                .seller(findSalesPerson(future))
                .paymentConditions(findPaymentConditions(future))
                .paymentDiscount(findPaymentDiscount(future))
                .build();
    }

    private JsonNode findPaymentDiscount(OrderFutureHolder future) {
        // update info from future (remote master data)
        JsonNode paymentDiscount = getNodeProperty(future.getCustomerPreferences(), "paymentDiscount");

        // in none found, use the current one in the orderLine
        try {
            return paymentDiscount.isNull() ? getPaymentDiscount(future.getOrder()) : transform(paymentDiscount, "/jolt/sales/orderPaymentDiscount-from-remote-SPEC.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode findPaymentConditions(OrderFutureHolder future) {
        // update info from future (remote master data)
        JsonNode paymentConditions = getNodeProperty(future.getCustomerPreferences(), "paymentConditions");

        // in none found, use the current one in the orderLine
        try {
            return paymentConditions.isNull() ? getPaymentCondition(future.getOrder()) : transform(paymentConditions, "/jolt/sales/orderPaymentConditions-from-remote-SPEC.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode findDeliveryInstructions(OrderFutureHolder future) {
        return getNodeProperty(future.getDeliveryPreferences(), "deliveryInstructions", getDeliveryInstructions(future.getOrder()));
    }

    private JsonNode findSalesPerson(OrderFutureHolder future) {
        return getNodeProperty(future.getSeller(), "seller", getSeller(future.getOrder()));
    }

    private JsonNode findCustomer(OrderFutureHolder future) {
        JsonNode customer = getFutureValue(future.getCustomer());
        try {
            return customer.isNull() ? getCustomer(future.getOrder()) : transform(customer, "/jolt/sales/customer-from-remote-SPEC.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode findBillingAddress(OrderFutureHolder future) {
        JsonNode billingAddress = getNodeById(getBillingAddressId(future.getOrder()), future.getBillingAddresses());
        try {
            return billingAddress.isNull()? getBillingAddress(future.getOrder()) : transform(billingAddress, "/jolt/sales/orderBillingAddress-from-remote.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Future<JsonNode> getRemoteDeliveryPreferences(JsonNode order) {
        String destinationAddressId = getDestinationAddressId(order);
        if (StringUtils.isNotBlank(destinationAddressId))
            return companyApi.getDeliveryPreferences(destinationAddressId, getSellerId(order), getCustomerId(order));
        return null;
    }

    private Future<JsonNode> getRemoteDeliveryAddresses(JsonNode order) {
        return companyApi.listDeliveryAddresses(getCustomerId(order));
    }

    private Future<JsonNode> getRemoteBillingAddresses(JsonNode order) {
        String destinationAddressId = getDestinationAddressId(order);
        if (StringUtils.isNotBlank(destinationAddressId))
            return companyApi.listBillingAddresses(getCustomerId(order), destinationAddressId);
        return null;
    }

    private Future<JsonNode> getRemoteSellers(JsonNode order) {
        return customerApi.listSellers(getCustomerId(order), getSellerId(order));
    }

    private Future<JsonNode> getRemoteCustomerPreferences(JsonNode order) {
        return customerApi.getCustomerPreferences(getCustomerId(order), getSellerId(order));
    }

    private Future<JsonNode> getRemoteCustomer(JsonNode order) {
        return customerApi.getCustomer(getCustomerId(order));
    }

}

package com.github.jmetzz.laboratory.json.service.remote;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.Future;

public interface CompanyAPI {

    Future<JsonNode> getDeliveryPreferences(String addressId, String sellerId, String customerId);

    Future<JsonNode> listBillingAddresses(String companyRef, String deliveryAddressId);

    Future<JsonNode> listDeliveryAddresses(String companyRef);

}

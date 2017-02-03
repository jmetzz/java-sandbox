package com.github.jmetzz.laboratory.json.service.remote;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.Future;

/**
 * Created by jean on 18/01/2017.
 */
public interface CustomerAPI {


    Future<JsonNode> getCustomer(String customerRef);

    Future<JsonNode> getCustomerPreferences(String customerRef, String sellerRef);

    Future<JsonNode> listSellers(String customerRef, String entityRef);

}

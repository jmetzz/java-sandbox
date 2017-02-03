package com.github.jmetzz.laboratory.json.service.remote;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.Future;

/**
 * Created by jean on 3/01/2017.
 */
public interface ProductAPI {

    Future<JsonNode> getProduct(String productId);

    Future<JsonNode> getPacking(String productId);
}

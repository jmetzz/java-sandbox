package com.github.jmetzz.laboratory.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jmetzz.laboratory.json.model.OrderLineMetadata;
import com.github.jmetzz.laboratory.json.service.remote.ProductAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.github.jmetzz.laboratory.json.util.OrderLineJsonUtils.*;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonTransformer.transform;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.getFutureValue;

@Component
public class OrderLineMetadataCollector {

    @Autowired
    private ProductAPI productApi;

    public OrderLineMetadata collect(JsonNode orderLine) {
        return orderLine == null ? null : OrderLineMetadata.newBuilder()
                .packaging(findPackaging(orderLine))
                .product(findProduct(orderLine))
                .build();
    }

    private JsonNode findProduct(JsonNode orderLine) {
        // update product info from remote master data
        JsonNode product = getRemoteProduct(orderLine);

        // in none found, use the current one in the orderLine
        try {
            return (product.isNull()) ? getProduct(orderLine) : transform(product, "/jolt/sales/orderProduct-from-remoteProduct-SPEC.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode getRemoteProduct(JsonNode orderLine) {
        return getFutureValue(productApi.getProduct(getProductId(orderLine)));
    }

    private JsonNode findPackaging(JsonNode orderLine) {
        // update packaging info from remote master data
        JsonNode packaging = getRemotePackaging(orderLine);

        // in none found, use the current one in the orderLine
        try {
            return packaging.isNull() ? getPackaging(orderLine) : transform(packaging, "/jolt/sales/orderPackaging-from-remotePackaging-SPEC.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonNode getRemotePackaging(JsonNode orderLine) {
        return getFutureValue(productApi.getPacking(getPackagingId(orderLine)));
    }

}

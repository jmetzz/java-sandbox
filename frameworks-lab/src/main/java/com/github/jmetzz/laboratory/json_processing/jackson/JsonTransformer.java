package com.github.jmetzz.laboratory.json_processing.jackson;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;

public class JsonTransformer {
    public static JsonNode transform(JsonNode json, String jsonSpec) {
        List<Object> chainSpecJSON = JsonUtils.classpathToList(jsonSpec);
        Chainr chainr = Chainr.fromSpec(chainSpecJSON);
        Object transform = chainr.transform(JsonUtils.jsonToObject(json.toString()));
        try {
            return new ObjectMapper().readTree(JsonUtils.toJsonString(transform));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNode(JsonNode targetNode, JsonNode nodeToAdd, String fieldName) {
        try {
            if (targetNode instanceof ObjectNode) {
                ((ObjectNode) targetNode).set(fieldName, nodeToAdd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

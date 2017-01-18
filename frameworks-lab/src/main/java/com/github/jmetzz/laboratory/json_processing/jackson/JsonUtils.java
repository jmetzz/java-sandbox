package com.github.jmetzz.laboratory.json_processing.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jean on 3/01/2017.
 */
public class JsonUtils {
    public static final NullNode NULL_NODE = JsonNodeFactory.instance.nullNode();
    private static final String DELIMITER = "\"";
    private static final String SEPARATOR = ":";

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getNodeProperty(JsonNode node, String propertyName) {
        return node == null || node.has(propertyName) == false ? NULL_NODE : node.get(propertyName);
    }

    public static JsonNode getNodeProperty(JsonNode node, String propertyName, JsonNode defaultValue) {
        return node == null || node.has(propertyName) == false ? defaultValue : node.get(propertyName);
    }

    public static String toJsonString(JsonNode node) throws JsonProcessingException {
        return mapper.writeValueAsString(node);
    }

    public static JsonNode toJson(String jsonStr) throws IOException {
        return mapper.readValue(jsonStr, JsonNode.class);
    }


    public static JsonNode toJson(String key, String value) throws IOException {
        StringBuilder sb = new StringBuilder()
                .append("{")
                .append(DELIMITER).append(key).append(DELIMITER)
                .append(SEPARATOR)
                .append(DELIMITER).append(value).append(DELIMITER)
                .append("}");
        return mapper.readValue(sb.toString(), JsonNode.class);
    }

    public static JsonNode getFutureValue(Future<JsonNode> futureNode) {
        try {
            return futureNode == null ? NULL_NODE : futureNode.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return NULL_NODE;
    }

    public static JsonNode getNodeProperty(Future<JsonNode> node, String propertyName, JsonNode defaultValue) {
        return getNodeProperty(getFutureValue(node), propertyName, defaultValue);
    }

    public static JsonNode getNodeProperty(Future<JsonNode> node, String propertyName) {
        return getNodeProperty(getFutureValue(node), propertyName);
    }


    public static boolean isNotNull(JsonNode jsonNode) {
        return !jsonNode.isNull();
    }


    public static void addNode(JsonNode targetNode, JsonNode nodeToAdd, String fieldName) {
        if (targetNode instanceof ObjectNode) {
            ((ObjectNode) targetNode).set(fieldName, nodeToAdd);
        }
    }

    public static void setNodeProperty(ObjectNode node, String key, JsonNode property) {
        if (isNotNull(property)) {
            node.set(key, property);
        }
    }
}

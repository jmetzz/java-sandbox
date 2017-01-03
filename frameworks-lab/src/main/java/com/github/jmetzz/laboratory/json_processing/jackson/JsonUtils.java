package com.github.jmetzz.laboratory.json_processing.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jean on 3/01/2017.
 */
public class JsonUtils {
    static final NullNode NULL_NODE = JsonNodeFactory.instance.nullNode();

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getNodeProperty(JsonNode node, String propertyName) {
        return node == null || node.has(propertyName) == false ? NULL_NODE : node.get(propertyName);
    }

    public static JsonNode getNodeProperty(JsonNode node, String propertyName, JsonNode defaultValue) {
        return node == null || node.has(propertyName) == false ? defaultValue : node.get(propertyName);
    }

    public static String toJsonString(JsonNode node) {
        try {
            return mapper.writeValueAsString(node);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public static JsonNode fromFile(String filename) throws IOException {
        return mapper.readTree(new File(filename));
    }

}

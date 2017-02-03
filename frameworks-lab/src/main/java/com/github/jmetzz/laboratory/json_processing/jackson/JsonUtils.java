package com.github.jmetzz.laboratory.json_processing.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jean on 3/01/2017.
 */
public class JsonUtils {
    public static final NullNode NULL_NODE = JsonNodeFactory.instance.nullNode();
    private static final String DELIMITER = "\"";
    private static final String SEPARATOR = ":";
    public static final String ID = "ID";

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getNodeProperty(JsonNode node, String propertyName) {
        return node == null || node.isNull() || node.has(propertyName) == false ? NULL_NODE : node.get(propertyName);
    }

    public static JsonNode getNodeProperty(JsonNode node, String propertyName, JsonNode defaultValue) {
        return node == null || node.isNull() || node.has(propertyName) == false ? defaultValue : node.get(propertyName);
    }

    public static String toJsonString(JsonNode node) throws RuntimeException {
        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode toJson(String jsonStr) throws RuntimeException {
        try {
            return mapper.readValue(jsonStr, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static JsonNode toJson(String key, String value) throws RuntimeException {
        StringBuilder sb = new StringBuilder()
                .append("{")
                .append(DELIMITER).append(key).append(DELIMITER)
                .append(SEPARATOR)
                .append(DELIMITER).append(value).append(DELIMITER)
                .append("}");

        try {
            return mapper.readValue(sb.toString(), JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode getFutureValue(Future<? extends JsonNode> futureNode) {
        try {
            return futureNode == null ? NULL_NODE : futureNode.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return NULL_NODE;
    }


    public static JsonNode getNodeProperty(Future<? extends JsonNode> node, String propertyName, JsonNode defaultValue) {
        return getNodeProperty(getFutureValue(node), propertyName, defaultValue);
    }

    public static JsonNode getNodeProperty(Future<? extends JsonNode> node, String propertyName) {
        return getNodeProperty(getFutureValue(node), propertyName);
    }

    public static JsonNode getNodeById(String id, Future<? extends JsonNode> nodes) {
        return getNodeById(id, getFutureValue(nodes));
    }

    public static JsonNode getNodeById(String id, JsonNode nodes) {
        if (StringUtils.isBlank(id)) {
            return NULL_NODE;
        }

        for (JsonNode node : nodes) {
            if (StringUtils.equals(id, node.get(ID).asText(null))) {
                return node;
            }
        }
        return NULL_NODE;
    }


    public static JsonNode fromFile(String fileName) {
        try {
            return new ObjectMapper().readTree(readFileToString(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFileToString(String path) {
        try {
            InputStream resourceAsStream = JsonUtils.class.getClassLoader().getResourceAsStream(path);
            return IOUtils.toString(new InputStreamReader(resourceAsStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

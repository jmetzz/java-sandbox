package com.github.jmetzz.laboratory.json_processing.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by jean on 3/01/2017.
 */
public class JsonUtilsTest {


    @Test
    public void createJsonNode() throws IOException {
        JsonNode jsonNode = JsonUtils.toJson("key", "value");
        assertThat(jsonNode.toString()).isEqualTo("{\"key\":\"value\"}");
    }

}
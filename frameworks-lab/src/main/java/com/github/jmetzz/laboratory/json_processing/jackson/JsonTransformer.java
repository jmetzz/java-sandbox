package com.github.jmetzz.laboratory.json_processing.jackson;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonTransformer {
    public static JsonNode transform(JsonNode json, String jsonSpec) throws IOException, JsonProcessingException{
        List<Object> chainSpecJSON = JsonUtils.classpathToList(jsonSpec);
        Chainr chainr = Chainr.fromSpec(chainSpecJSON);
        Object transform = chainr.transform(JsonUtils.jsonToObject(json.toString()));
        return new ObjectMapper().readTree(JsonUtils.toJsonString(transform));
    }
}

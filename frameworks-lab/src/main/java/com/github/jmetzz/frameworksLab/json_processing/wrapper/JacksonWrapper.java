package com.github.jmetzz.frameworksLab.json_processing.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;


public class JacksonWrapper implements JsonParserWrapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public Object serialize(String jsonStr) throws IOException {
        return mapper.readValue(jsonStr, Object.class);
    }

    public <T> T serialize(String jsonStr, Class<T> clazz) throws IOException {
        return mapper.readValue(jsonStr, clazz);
    }

    public Map[] serialize(Path source) throws IOException {
        return mapper.readValue(new FileReader(source.toFile()), Map[].class);
    }

    public String deserialize(Object obj) throws JsonProcessingException {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper.writeValueAsString(obj);
    }
}

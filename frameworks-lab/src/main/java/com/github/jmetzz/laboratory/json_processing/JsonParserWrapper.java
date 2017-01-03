package com.github.jmetzz.laboratory.json_processing;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface JsonParserWrapper {

    Object serialize(String jsonStr) throws IOException;

    <T> T serialize(String jsonStr, Class<T> clazz) throws IOException;

    Map[] serialize(Path source) throws IOException;

    String deserialize(Object obj) throws JsonProcessingException;


}

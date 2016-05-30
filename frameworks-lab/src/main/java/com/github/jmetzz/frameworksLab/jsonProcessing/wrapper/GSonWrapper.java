package com.github.jmetzz.frameworksLab.jsonProcessing.wrapper;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.nio.file.Path;
import java.util.Map;


public class GSonWrapper implements JsonParserWrapper {

    private static Gson gson = new Gson();

    public Object serialize(String jsonStr) {
        return gson.fromJson(jsonStr, Object.class);
    }

    public <T> T serialize(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }

    public Map[] serialize(Path source) throws FileNotFoundException {
        return gson.fromJson(new FileReader(source.toFile()), Map[].class);
    }

    public String deserialize(Object obj) {
        return gson.toJson(obj);
    }
}

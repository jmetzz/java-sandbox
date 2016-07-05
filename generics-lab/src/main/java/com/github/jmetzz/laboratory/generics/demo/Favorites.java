package com.github.jmetzz.laboratory.generics.demo;


import java.util.HashMap;
import java.util.Map;

public class Favorites {

    private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void putFavorite(Class<T> key, T value) {
        if (key == null)
            throw new NullPointerException("Type is null");

        map.put(key, value);
    }

    public <T> T getFavorite(Class<T> key) {
        return key.cast(map.get(key));
    }
}

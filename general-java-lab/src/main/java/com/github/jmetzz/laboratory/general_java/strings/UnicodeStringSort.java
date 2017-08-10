package com.github.jmetzz.laboratory.general_java.strings;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sorts Strings in Unicode order.
 */
public class UnicodeStringSort {

    public static void sort(List<String> list){
        Collections.sort(list);
    }
    public static Map<String, String> sort(Map<String, String> map){
        TreeMap<String, String> result = new TreeMap<>();
        result.putAll(map);
        return result;
    }
}

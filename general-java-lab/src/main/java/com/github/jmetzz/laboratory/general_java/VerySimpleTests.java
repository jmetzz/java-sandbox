package com.github.jmetzz.laboratory.general_java;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class VerySimpleTests {


    public static void main(String[] args) {

        SortedSet<String> set = new TreeSet<>();

        set.add("a");
        set.add("ab");
        set.add("aa");
        set.add("aba");


        System.out.println(set);
        System.out.println(Collections.binarySearch(new ArrayList<>(set), "aa"));

    }

}

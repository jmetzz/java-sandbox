package com.github.jmetzz.basics.collections;

import java.util.*;

public class WordFrequency {

    public static void main(String[] args) {

        String[] input = new String[] {"Java", "Coffe", "Collections", "Coffe", "Toffe"};

        Map<String, Integer> m = new LinkedHashMap<String, Integer>();
        System.out.println("Original words:");
        System.out.println(Arrays.asList(input));


        // Initialize frequency table from command line
        for (String a : input) {
            Integer freq = m.get(a);
            m.put(a, (freq == null) ? 1 : freq + 1);
        }

        System.out.println(m.size() + " distinct words!");

        System.out.println("----");
        System.out.println("Sorted list (appearing order) of word");
        System.out.println(m);

        System.out.println("----");
        System.out.println("List of word and frequencies in no particular order");
        System.out.println(new HashMap<String, Integer>(m));

        System.out.println("----");
        System.out.println("Sorted list (alphabeticaly) of word");
        System.out.println(new TreeMap<String, Integer>(m));
    }
}

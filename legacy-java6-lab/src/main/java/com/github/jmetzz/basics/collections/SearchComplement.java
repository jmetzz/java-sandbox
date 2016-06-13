package com.github.jmetzz.basics.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SearchComplement {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Integer x, sum;
        x = 4;
        sum = 16;
        int capacity = 30;

        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random(System.currentTimeMillis());
        for (int e = 0; e < capacity; ++e) {
            list.add(r.nextInt(capacity));
        }

        int diff = sum - x;
        Collections.sort(list);
        int found = Collections.binarySearch(list, diff);
        if (found >= 0)
            System.out.println("Element found: " + list.get(found));
        else
            System.out.println("No complement found");

    }

}

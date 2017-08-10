package com.github.jmetzz.challenges.problems.booking_com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SearchComplement {

    /**
     * @param args
     */
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random(System.currentTimeMillis());
        int capacity = 30;

        for (int e = 0; e < capacity; ++e) {
            list.add(r.nextInt(capacity));
        }

        Integer x = 4;
        Integer ceiling = 20;
        int index = findComplement(list, x, ceiling);

        if (index >= 0)
            System.out.println("Element found: " + list.get(index));
        else
            System.out.println("No complement found");


        index = findPairThatMatchGivenSum(list, ceiling);
        if (index >= 0)
            System.out.println("Element found: " + list.get(index));
        else
            System.out.println("No complement found");

    }

    private static int findComplement(List<Integer> list, int x, int max) {
        Collections.sort(list);
        int diff = max - x;
        return Collections.binarySearch(list, diff);
    }

    private static int findPairThatMatchGivenSum(List<Integer> list, int sum) {
        Collections.sort(list);
        int complement = -1;
        for (int i = 0; i < list.size() && complement < 0; i++) {
            int diff = sum - list.get(i);
            complement = Collections.binarySearch(list.subList(i, list.size()), diff);
        }
        return complement;
    }

}

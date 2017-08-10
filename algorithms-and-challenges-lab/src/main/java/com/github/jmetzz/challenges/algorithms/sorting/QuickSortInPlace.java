package com.github.jmetzz.challenges.algorithms.sorting;

import java.util.Arrays;

public class QuickSortInPlace {


    public static void main(String[] args) {
        Integer[] a = {3, 7, 5, 2, 1, 5, 4};
        System.out.println(Arrays.asList(a));
        sort(a);
        System.out.println(Arrays.asList(a));
    }

    public static void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = partition(array, lo, hi);
        sort(array, lo, pivot - 1);
        sort(array, pivot + 1, hi);
    }

    private static int partition(Comparable[] array, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        Comparable value = array[lo]; //pivot

        while (true) {
            // will stop when array[i] >= value
            // Thus, array[i] should be swapped with
            // lower element
            while (less(array[++i], value))
                if (i == hi) break;

            // will stop when array[i] <= value
            // Thus, array[i] should be swapped with
            // higher element
            while (less(value, array[--j]))
                if (j == lo) break;

            // check if pointers crossed the array is already partitioned
            if (i >= j) break;

            swap(array, i, j);
        }

        // put partitioning item v at a[j]
        swap(array, lo, j);
        return j;
    }


    /***************************************************************************
     * Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


}

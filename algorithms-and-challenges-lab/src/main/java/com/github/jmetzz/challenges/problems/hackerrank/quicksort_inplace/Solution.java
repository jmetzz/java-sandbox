package com.github.jmetzz.challenges.problems.hackerrank.quicksort_inplace;


import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Jean Metz.
 */
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        sort(array);

    }

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = partition(array, lo, hi);
        sort(array, lo, pivot - 1);
        sort(array, pivot + 1, hi);
    }

    private static int partition(int[] array, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        int value = array[lo];

        while (true) {
            while (array[++i] < value)
                if (i == hi) break;

            while (value < array[--j])
                if (j == lo) break;

            if (i >= j) break;

            swap(array, i, j);
        }

        swap(array, lo, j);
        printArray(array);
        return j;
    }

    private static void printArray(int[] array) {
        Arrays.stream(array).forEach(System.out::print);
        System.out.println("");
    }


    // exchange a[i] and a[j]
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}

package com.github.jmetzz.laboratory.general_java.httpClient;


import java.util.*;
import java.util.stream.Collectors;

public class ScannerBasedCmdReader {


    /*
     * let's read 2 lines containing integer elements, N and K.
      * The first line has only 2 elements, while the second
      * line has N integer elements that we store in a heap object.
      * print out the list of elements sorted.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        Integer[] heap = new Integer[n];
        for(int i = 0; i < n;  i++){
            heap[i] = in.nextInt();
        }
        System.out.println("N is:" + n);
        System.out.println("K is:" + k);
        System.out.println("Sorted elements are: ");

        System.out.println(Arrays.stream(heap).sorted().collect(Collectors.toList()));

    }


}

package com.github.jmetzz.challenges.algorithms.heaps;


import java.util.Scanner;

public class HeapUsageAppDemo {

    public static void main(String[] args) {
       /* Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        Integer[] heap = new Integer[n];
        for (int i = 0; i < n; i++) {
            heap[i] = in.nextInt();
        }*/

        int n = 6;
        int k = 7;
//        Integer[] heap = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        Integer[] heap = {1, 2, 3, 9, 10, 12};

//        System.out.println(Arrays.stream(heap)
//                .collect(toList()));


        int sweetness = 0;
        int it = 0;
        while (sweetness < k && it < n) {
            heap = HeapBuilder.builMindHeap(heap);

            int s1 = heap[0];
            heap[0] = Integer.MAX_VALUE;

            heap = HeapBuilder.builMindHeap(heap);

            int s2 = heap[0];
            sweetness = s1 + (2 * s2);
            heap[0] = sweetness;
            it++;
        }

        System.out.println(it);


//        System.out.println(Arrays.stream(heap).collect(toList()));

    }

}

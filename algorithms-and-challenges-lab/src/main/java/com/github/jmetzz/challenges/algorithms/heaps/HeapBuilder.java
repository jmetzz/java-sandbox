package com.github.jmetzz.challenges.algorithms.heaps;


import java.util.Arrays;

public class HeapBuilder {


    public static Integer[] builMindHeap(Integer[] array) {
        Integer[] heap = Arrays.copyOf(array, array.length);
        int boundary = (array.length / 2) - 1;
        for (int i = boundary; i >= 0; i--) {
            heapify(heap, i);
        }
        return heap;
    }

    private static void heapify(Integer[] array, int index) {
        int smallest = index;
        int l = left(index);
        if (l < array.length && array[l] < array[smallest])
            smallest = l;

        int r = right(index);
        if (r < array.length && array[r] < array[smallest])
            smallest = r;

        if (smallest != index)
            swap(array, index, smallest);
    }


    private static void swap(Integer[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static int left(int i) {
        return i * 2 + 1;
    }

    private static int right(int i) {
        return i * 2 + 2;
    }
}

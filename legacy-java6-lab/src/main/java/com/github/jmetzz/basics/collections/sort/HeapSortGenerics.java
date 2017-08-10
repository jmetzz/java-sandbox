package com.github.jmetzz.basics.collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapSortGenerics {

	
	public static <T extends Comparable<? super T>> void heapSort(T[] v) {
        buildMaxHeap(v);
        int n = v.length;
 
        for (int i = v.length - 1; i > 0; i--) {
            swap(v, i, 0);
            maxHeapify(v, 0, --n);
        }
    }
 
    private static <T extends Comparable<? super T>> void buildMaxHeap(T v[]) {
        for (int i = v.length / 2 - 1; i >= 0; i--)
            maxHeapify(v, i, v.length);
    }
 
    private static <T extends Comparable<? super T>> void maxHeapify(T[] v, int pos,
            int n) {
        int max = 2 * pos + 1, right = max + 1;
        if (max < n) {
            if (right < n && v[max].compareTo(v[right]) < 0)
                max = right;
            if (v[max].compareTo(v[pos]) > 0) {
                swap(v, max, pos);
                maxHeapify(v, max, n);
            }
        }
    }
 
    public static void swap(Object[] v, int j, int aposJ) {
        Object aux = v[j];
        v[j] = v[aposJ];
        v[aposJ] = aux;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int capacity = Integer.parseInt(args[0]);
		int capacity = 1000;
		List<Double> list = new ArrayList<Double>(capacity);
		Random r = new Random(System.currentTimeMillis());
		for(int e = 0; e < capacity; ++e){
			list.add(r.nextDouble());
		}

		long sTime = System.currentTimeMillis();
		heapSort(list.toArray(new Integer[0]));
		long eTime = System.currentTimeMillis() - sTime;
		System.out.println("Heap sort elapsed time millis: " + eTime);


	}

}

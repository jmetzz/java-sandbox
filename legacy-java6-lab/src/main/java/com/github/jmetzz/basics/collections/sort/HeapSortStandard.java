package com.github.jmetzz.basics.collections.sort;

import java.util.Random;

public class HeapSortStandard {

	public static void heapSort(double[] v) {
		buildMaxHeap(v);
		int n = v.length;

		for (int i = v.length - 1; i > 0; i--) {
			swap(v, i, 0);
			maxHeapify(v, 0, --n);
		}
	}

	private static void buildMaxHeap(double[] v) {
		for (int i = v.length / 2 - 1; i >= 0; i--)
			maxHeapify(v, i, v.length);
	}

	private static void maxHeapify(double[] v, int pos, int n) {
		int maxi;
		int l = 2 * pos + 1;
		int right = 2 * pos + 2;
		if ((l < n) && (v[l] > v[pos])) {
			maxi = l;
		} else {
			maxi = pos;
		}
		if (right < n && v[right] > v[maxi]) {
			maxi = right;
		}
		if (maxi != pos) {
			swap(v, pos, maxi);
			maxHeapify(v, maxi, n);
		}
	}

	public static void swap(double[] v, int j, int aposJ) {
		double aux = v[j];
		v[j] = v[aposJ];
		v[aposJ] = aux;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int capacity = Integer.parseInt(args[0]);
		int capacity = 1000;
		double[] array = new double[capacity];
		Random r = new Random(System.currentTimeMillis());
		for (int e = 0; e < capacity; ++e) {
			array[e] = r.nextDouble();
		}
		long sTime = System.currentTimeMillis();
		heapSort(array);
		long eTime = System.currentTimeMillis() - sTime;
		System.out.println("Heap sort elapsed time millis: " + eTime);

	}

}

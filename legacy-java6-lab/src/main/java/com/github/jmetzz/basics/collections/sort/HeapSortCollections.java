package com.github.jmetzz.basics.collections.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class HeapSortCollections {

	public static <E> List<E> heapSort(Collection<E> c) {
		Queue<E> queue = new PriorityQueue<E>(c);
		List<E> result = new ArrayList<E>();

		while (!queue.isEmpty())
			result.add(queue.remove());

		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int capacity = 20;
		List<Integer> list = new ArrayList<Integer>(capacity);
		Random r = new Random(1);
		for(int e = 0; e < capacity; ++e){
			list.add(r.nextInt());
		}
		
		System.out.println(list);

		long sTime = System.currentTimeMillis();
		heapSort(list); // Heap Sort implemented with Collections Framework infra.
		long eTime = System.currentTimeMillis() - sTime;
		System.out.println("Heap sort elapsed time millis: " + eTime);

	}

}

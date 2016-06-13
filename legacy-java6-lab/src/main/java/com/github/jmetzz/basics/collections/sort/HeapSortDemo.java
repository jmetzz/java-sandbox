package com.github.jmetzz.basics.collections.sort;
import java.util.*;


public class HeapSortDemo {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 1 ) {
			System.err.println("Usage: \tjava HeapSortCollections <int:capacity>");
			System.err.println("\t where capacity is the collections size");
			
			System.exit(-1);
		}
		int capacity = 0;
		try{
			capacity = Integer.parseInt(args[0]);
			if (capacity < 1) 
				throw new NumberFormatException("capacity should be greater than 0"); 
		} catch (NumberFormatException e){
			System.err.println("Usage: \tjava HeapSortCollections <int:capacity>");
			System.err.println("\t where capacity is the collections size");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Starting...");
		
			
		double[] array = new double[capacity];
		List<Double> list = new ArrayList<Double>(capacity);
		Random r = new Random(System.currentTimeMillis());
		for(int e = 0; e < capacity; ++e){
			array[e] = r.nextDouble();
			list.add(array[e]);
		}
		System.out.println(list);
		
		long sTime = System.currentTimeMillis();
		HeapSortStandard.heapSort(array); 
		long eTime = System.currentTimeMillis() - sTime;
		System.out.println("Standard Heap sort elapsed time millis: " + eTime);
		array = null;

		Double[] dArray = list.toArray(new Double[0]);
		sTime = System.currentTimeMillis();
		HeapSortGenerics.heapSort(dArray); 
		eTime = System.currentTimeMillis() - sTime;
		System.out.println("Generics Heap sort elapsed time millis: " + eTime);
		dArray = null;

		sTime = System.currentTimeMillis();
		HeapSortCollections.heapSort(list); 
		eTime = System.currentTimeMillis() - sTime;
		System.out.println("MyCollections Heap sort elapsed time millis: " + eTime);
		
		
		sTime = System.currentTimeMillis();
		Collections.sort(list); // Collections default sort method: Merge Sort
		eTime = System.currentTimeMillis() - sTime;
		System.out.println("Merge sort elapsed time millis: " + eTime);

	}

}

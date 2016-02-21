package com.github.jmetzz.concurrency.standalone.threadPoolExample;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jean Metz.
 */
public class AppFixedThreadPool {
    public final static int THREAD_POOL_SIZE = 5;

    public static Map<String, Integer> hashTable = null;
    public static Map<String, Integer> synchronizedMap = null;
    public static Map<String, Integer> concurrentHashMap = null;


    public static void main(String[] args) throws InterruptedException {

        hashTable = new Hashtable<String, Integer>();
        synchronizedMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        concurrentHashMap = new ConcurrentHashMap<String, Integer>();

        measurePerformance(hashTable);
        measurePerformance(synchronizedMap);
        measurePerformance(concurrentHashMap);

    }

    public static void measurePerformance(final Map<String, Integer> map) throws InterruptedException {

        System.out.println("Test started for: " + map.getClass());
        long averageTime = 0;
        //iterate 10 times to get the average time
        for (int i = 0; i < 5; i++) {

            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            for (int j = 0; j < THREAD_POOL_SIZE * 2; j++) {
                executorService.execute(new MapModifier(map));
            }

            // Make sure executor stops
            executorService.shutdown();

            // Blocks until all tasks have completed execution after a shutdown request
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entries added/retrieved in " + totalTime + " ms");
        }
        System.out.println("For " + map.getClass() + " the average time is " + averageTime / 10 + " ms\n");
    }
}

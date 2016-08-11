package com.github.jmetzz.challenges.problems.booking_com.happinessScore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by Jean Metz.
 */
public class MTHSPowerSet1Step {

    private static final int THREAD_POOL_SIZE = 10;
    private static Logger logger = Logger.getLogger(MTHSPowerSet1Step.class.getName());

    private BlockingQueue<List<Integer>> queue;

    private AtomicInteger counter;


    public MTHSPowerSet1Step() {
        queue = new LinkedBlockingQueue<>();
        counter = new AtomicInteger();
    }

    private static int sum(List<Integer> list) {
        int total = 0;
        for (Integer e : list) {
            total += e;
        }
        return total;
    }

    public static boolean isPrimeNumber(int number) {

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cInput = null;
        String ssInput = null;

        cInput = br.readLine();
        ssInput = br.readLine();

        int N = Integer.parseInt(cInput);

        String[] scoresStr = ssInput.split(" ");
        if (N != scoresStr.length)
            System.exit(-1);

        List<Integer> initialSet = new ArrayList<>();
        for (String s : scoresStr) {
            initialSet.add(Integer.parseInt(s));
        }
// initialSet.clear();
// initialSet.addAll(Arrays.asList(new Integer[]{3, 2, 6}));

        MTHSPowerSet1Step hss = new MTHSPowerSet1Step();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        executorService.execute(new Producer(hss.queue, initialSet));

        for (int j = 0; j < THREAD_POOL_SIZE; j++) {
            executorService.execute(new Consumer(hss.queue, hss.counter));
        }

        // Make sure executor stops
        executorService.shutdown();

        // Blocks until all tasks have completed execution after a shutdown request
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(hss.counter.get());
    }

    private static class Producer<T> implements Runnable {
        private final BlockingQueue<List<T>> queue;
        private final Collection<T> collection;

        Producer(BlockingQueue<List<T>> q, Collection<T> collection) {
            this.queue = q;
            this.collection = collection;
        }

        public void run() {
            generatePowerset();
        }


        public void generatePowerset() {
            List<List<T>> ps = new ArrayList<>();
            ps.add(new ArrayList<T>()); // add the empty set

            // for every item in the original list
            for (T item : collection) {
                List<List<T>> newPs = new ArrayList<>();

                for (List<T> subset : ps) {
                    // copy all of the current powerset's subsets
                    newPs.add(subset);

                    // plus the subsets appended with the current item
                    List<T> newSubset = new ArrayList<T>(subset);
                    newSubset.add(item);
                    newPs.add(newSubset);
                    queue.add(newSubset);
                    System.out.println("Produced: " + newSubset);
                }
                // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
                ps = newPs;
            }
            System.out.println(ps);
        }
    }

    private static class Consumer<T extends Integer> implements Runnable {
        private final BlockingQueue<List<T>> queue;
        private final AtomicInteger counter;

        Consumer(BlockingQueue<List<T>> queue, AtomicInteger counter) {
            this.queue = queue;
            this.counter = counter;
        }

        public void run() {
            try {
                while (!queue.isEmpty()) {
                    consume((List<Integer>) queue.take());
                }
            } catch (InterruptedException ex) {
                logger.info("woke up");
            }
        }

        void consume(List<Integer> list) {
            if (isPrimeNumber(sum(list)))
                counter.getAndIncrement();
            System.out.println("Consumed: " + list);
        }
    }


}

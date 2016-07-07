package com.github.jmetzz.laboratory.concurrency.standalone.threadPoolExample.executors;

import com.github.jmetzz.laboratory.concurrency.standalone.synchronizationExample.runnables.AtomicCounterRunnable;

import java.util.concurrent.*;

/**
 * Created by Jean Metz.
 */
public class AppThreadPoolExecutor {

    public static void main(String[] args) {

        // keep the jobs in this queue
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);

        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                int currentCount = AtomicCounterRunnable.COUNTER.getAndIncrement();
                System.out.println("Creating new thread: " + currentCount);
                return new Thread(r, "myThread" + currentCount);
            }
        };

        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof AtomicCounterRunnable) {
                    AtomicCounterRunnable example = (AtomicCounterRunnable) r;
                    System.out.println("Rejecting task with id " + example.getTaskId());
                }
            }
        };

        /*
         The ThreadPoolExecutor starts with 5 core threads
         and allows the pool to grow up to 10 threads at the maximum.
         Any other task is automatically rejected
          */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, queue, threadFactory, rejectedHandler);
        for (int i = 0; i < 100; i++) {
            executor.execute(new AtomicCounterRunnable(i, 2000));
        }

        try {
            System.out.println("Sleeping to give some time for tasks to be finished" +
                    " and free some slots on the thread pool");
            Thread.sleep(2000);
            System.out.println("Wake up!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Creating new tasks");
        for (int i = 100; i < 150; i++) {
            executor.execute(new AtomicCounterRunnable(i, 1000));
        }

        // Make sure executor stops
        executor.shutdown();

        // Blocks until all tasks have completed execution after a shutdown request
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----> End. Some tasks might finish later....");
    }
}

package com.github.jmetzz.concurrency.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jean Metz.
 */
public class ThreadPoolExecutorExample implements Runnable {
    private static AtomicInteger counter = new AtomicInteger();
    private final int taskId;

    public ThreadPoolExecutorExample(int taskId) {
        this.taskId = taskId;
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);

        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                int currentCount = counter.getAndIncrement();
                System.out.println("Creating new thread: " + currentCount);
                return new Thread(r, "myThread" + currentCount);
            }
        };

        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof ThreadPoolExecutorExample) {
                    ThreadPoolExecutorExample example = (ThreadPoolExecutorExample) r;
                    System.out.println("Rejecting task with id " + example.getTaskId());
                }
            }
        };

        /*
         The ThreadPoolExecutor starts with 5 core threads
         and allows the pool to grow up to 10 threads at the maximum.
          */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, queue, threadFactory, rejectedHandler);
        for (int i = 0; i < 100; i++) {
            executor.execute(new ThreadPoolExecutorExample(i));
        }
        executor.shutdown();
    }

    public int getTaskId() {
        return taskId;
    }

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
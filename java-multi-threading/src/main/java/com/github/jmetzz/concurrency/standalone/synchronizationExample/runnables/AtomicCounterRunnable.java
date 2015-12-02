package com.github.jmetzz.concurrency.standalone.synchronizationExample.runnables;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jean Metz.
 */
public class AtomicCounterRunnable implements Runnable {

    public static AtomicInteger COUNTER = new AtomicInteger();

    private final int taskId;

    private final long sleepTime;

    public AtomicCounterRunnable(int taskId, long sleepTime) {
        this.taskId = taskId;
        this.sleepTime = sleepTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public void run() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\tTask done: " + taskId);
    }
}
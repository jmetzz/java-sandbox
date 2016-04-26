package com.github.jmetzz.multithread.threadPoolExample;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jean Metz.
 */
public class atomicCounterRunnable implements Runnable {

    private static AtomicInteger counter = new AtomicInteger();

    private final int taskId;

    public atomicCounterRunnable(int taskId) {
        this.taskId = taskId;
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
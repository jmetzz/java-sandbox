package com.github.jmetzz.multithread;

import java.util.Random;

/**
 * Created by Jean Metz on 19-Nov-15.
 */
public class SimpleRunnable implements Runnable {

    private Random rand = new Random(System.currentTimeMillis());

    private long steps;

    SimpleRunnable(long steps) {
        this.steps = steps;
    }

    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] started.");
        //simulate some CPU expensive task
        for (long i = 0L; i < steps; i++) {
            rand.nextInt();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] finished.");
    }

}

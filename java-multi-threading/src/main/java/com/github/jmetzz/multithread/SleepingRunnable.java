package com.github.jmetzz.multithread;

import java.util.Random;

/**
 * Created by Jean Metz on 19-Nov-15.
 */
public class SleepingRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            doSomethingUseful();
            try {
                Thread.sleep(1000 * (new Random()).nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void doSomethingUseful() {
        System.out.println("Doing something important...");
    }
}

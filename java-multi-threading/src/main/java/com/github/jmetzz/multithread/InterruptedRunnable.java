package com.github.jmetzz.multithread;

/**
 * Created by Jean Metz
 */
public class InterruptedRunnable implements Runnable {

    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Started!");
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] Interrupted by exception!");
        }
        while (!Thread.interrupted()) {
            // do nothing here
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Interrupted for the second time.");
    }

}

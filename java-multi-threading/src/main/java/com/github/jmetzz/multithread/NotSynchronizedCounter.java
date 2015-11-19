package com.github.jmetzz.multithread;

/**
 * Created by Jean Metz.
 */
public class NotSynchronizedCounter implements Runnable {
    private static int counter = 0;

    public void run() {
        while (counter < 10) {
            System.out.println("[" + Thread.currentThread().getName() + "] before: " + counter);
            counter++;
            System.out.println("[" + Thread.currentThread().getName() + "] after: " + counter);
        }
    }
}

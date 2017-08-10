package com.github.jmetzz.laboratory.concurrency.standalone.synchronizationExample.runnables;

/**
 * Created by Jean Metz.
 */
public class NotSynchronizedCounter implements Runnable {
    private static int counter = 0;

    public void run() {
        System.out.println(" *** Stating *** " + Thread.currentThread().getName());
        while (counter < 100) {
            System.out.println("\t[" + Thread.currentThread().getName() + "] before: " + counter);
            counter++;
            System.out.println("\t[" + Thread.currentThread().getName() + "] after: " + counter);
        }
    }
}

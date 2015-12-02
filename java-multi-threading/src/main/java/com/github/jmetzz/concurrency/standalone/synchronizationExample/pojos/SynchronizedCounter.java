package com.github.jmetzz.concurrency.standalone.synchronizationExample.pojos;

/**
 * Created by Jean Metz.
 */
public class SynchronizedCounter {

    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }

}

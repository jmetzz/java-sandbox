package com.github.jmetzz.concurrency.standalone.synchronizationExample.pojos;

/**
 * Created by Jean Metz.
 */
public class Counter {

    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

}

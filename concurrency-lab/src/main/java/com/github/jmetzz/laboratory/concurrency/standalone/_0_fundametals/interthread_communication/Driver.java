package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.interthread_communication;


public class Driver {

    /**
     * Driver class to bootstrap a very simplistic example
     * of interthread communication via wait() and notify() methods
     * by using a simplified queue (class Q)
     * Beware there are better options for this task :)
     */

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Ctrl+c to stop");
    }
}

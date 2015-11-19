package com.github.jmetzz.multithread;

/**
 * Created by Jean Metz.
 */
public class SynchronizedCounter implements Runnable {
    private static int counter = 0;

    public void run() {

            /*
            The synchronized(SynchronizedCounter.class) statement works like a barrier
            where all threads have to stop and ask for entrance. Only the first thread
            that gets the lock on the resources is allowed to pass. Once it has left
            the synchronized block, another waiting thread can enter and so forth.
            Check the difference of theses cases:
             - synchronize the whole method ... public synchronized void run () {...}
             - synchronize only the code within the while loop
             - synchronize the while loop as a whole, ie,
                     synchronized (SynchronizedCounter.class) {
                        while (counter < 10) {..}
                     }
             */
        synchronized (SynchronizedCounter.class) {
            while (counter < 10) {
                System.out.println("[" + Thread.currentThread().getName() + "] before: " + counter);
                counter++;
                System.out.println("[" + Thread.currentThread().getName() + "] after: " + counter);
            }
        }
    }
}

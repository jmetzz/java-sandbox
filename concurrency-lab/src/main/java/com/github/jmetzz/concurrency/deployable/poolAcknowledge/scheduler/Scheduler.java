package com.github.jmetzz.concurrency.deployable.poolAcknowledge.scheduler;

import com.github.jmetzz.concurrency.deployable.poolAcknowledge.handler.IHandler;

/**
 * @author Jean Metz.
 *         Simlulates a timer service to run locally and standalone
 */
public class Scheduler implements Runnable {

    private final IHandler handler;
    private final long nextTimeout;
    private final int iterations;

    public Scheduler(IHandler handler, long nextTimeout, int iterations) {
        this.handler = handler;
        this.nextTimeout = nextTimeout;
        this.iterations = iterations;
    }

    @Override
    public void run() {

        for (int i = 0; i < iterations; i++) {
            try {
                Thread.sleep(nextTimeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----> Timeout event");
            handler.handle();
        }
        System.out.println("Scheduler canceled");
    }
}

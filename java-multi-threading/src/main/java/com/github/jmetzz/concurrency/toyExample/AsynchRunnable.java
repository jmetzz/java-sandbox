package com.github.jmetzz.concurrency.toyExample;

import java.util.Random;

/**
 * Created by Jean Metz on 14-Nov-15.
 */
public class AsynchRunnable implements Runnable {

    private String name;

    public AsynchRunnable(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println("Running thread " + name);

        try {
            Thread.sleep(new Random().nextInt(4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finishing thread " + name);
    }
}

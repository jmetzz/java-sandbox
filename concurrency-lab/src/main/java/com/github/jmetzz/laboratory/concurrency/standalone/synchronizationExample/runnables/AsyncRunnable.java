package com.github.jmetzz.laboratory.concurrency.standalone.synchronizationExample.runnables;

import com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.annotation.Immutable;

import java.util.Random;

/**
 * Created by Jean Metz
 */
@Immutable
public class AsyncRunnable implements Runnable {

    private final String name;

    public AsyncRunnable(String name) {
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

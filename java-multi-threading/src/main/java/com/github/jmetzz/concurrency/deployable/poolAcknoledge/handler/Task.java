package com.github.jmetzz.concurrency.deployable.poolAcknoledge.handler;

import com.github.jmetzz.concurrency.deployable.poolAcknoledge.Subscribers;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable<Subscribers.Subscriber> {

    private static final Logger LOGGER = Logger
            .getLogger(Task.class);

    private final Subscribers.Subscriber subscriber;

    public Task(Subscribers.Subscriber subscriber) {
        this.subscriber = subscriber;
    }


    public Subscribers.Subscriber call() {
        System.out.println("Starting task for subscriber " + subscriber);
        try {
            Thread.sleep((long) (new Random().nextInt(10)));
        } catch (InterruptedException e) {
            System.out.println("Interrupted from sleep");
        }

        System.out.println("Task done for " + subscriber);
        return this.subscriber;
    }

}

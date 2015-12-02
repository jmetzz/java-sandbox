package com.github.jmetzz.concurrency.deployable.poolAcknoledge;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Callable;

public class Worker implements Callable<Subscribers.Subscriber> {

    private static final Logger LOGGER = Logger
            .getLogger(Worker.class);

    private final Subscribers.Subscriber subscriber;

    public Worker(Subscribers.Subscriber subscriber) {
        this.subscriber = subscriber;
    }


    public Subscribers.Subscriber call() {
        System.out.println("Starting task for subscriber " + subscriber);
        try {
            Thread.sleep((long) (new Random().nextInt(10)));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("Interrupted from sleep");
        }

        System.out.println("Task done for " + subscriber);
        return this.subscriber;
    }

}

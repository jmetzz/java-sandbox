package com.github.jmetzz.concurrency.deployable.poolAcknoledge;

import com.google.common.base.MoreObjects;
import org.apache.log4j.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


public class HandlerImpl implements IHandler {

    private static final Logger LOGGER = Logger
            .getLogger(HandlerImpl.class);

    private static final int MAX_WORKERS = 10;
    private final CompletionService<Subscribers.Subscriber> completionService;
    private final Subscribers subscribers;
    private /* static */ AtomicInteger count = new AtomicInteger(0);
    private  /* static */ Map<Subscribers.Subscriber, Integer> allocationMap = Collections.synchronizedMap(new HashMap<Subscribers.Subscriber, Integer>());


    public HandlerImpl(CompletionService<Subscribers.Subscriber> completionService, Subscribers subscribers) {
        this.completionService = completionService;
        this.subscribers = subscribers;
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void handle() {
        LOGGER.debug("Handling scheduled task.");

        if (count.get() < MAX_WORKERS) {

            for (int i = 0; i < subscribers.size(); i++) {
                createWorkers(subscribers.get(i));
            }

            try {
                for (int j = 0; j < count.get(); j++) {
                    // here we just wait for task completion
                    Future<Subscribers.Subscriber> result = completionService.take();
                    if (result != null) {
                        Subscribers.Subscriber s = result.get();
                        synchronized (this) {
                            count.decrementAndGet();
                            int qtt = allocationMap.get(s) - 1;
                            allocationMap.put(s, qtt);
                        }
                    }
                    LOGGER.debug("Tasks completed");
                }
            } catch (InterruptedException e) {
                LOGGER.debug("Scheduled task interrupted.");
                System.out.println("Scheduled task interrupted.");
            } catch (ExecutionException e) {
                // thrown when attempting to retrieve the result of a task that aborted by throwing an exception
                LOGGER.debug("Scheduled task execution problem.");
                System.out.println("Scheduled task execution problem.");
            }
        }


    }

    private void createWorkers(Subscribers.Subscriber subscriber) {

        if (!allocationMap.containsKey(subscriber))
            allocationMap.put(subscriber, 0);

        int w = allocationMap.get(subscriber);

        for (; count.get() < MAX_WORKERS && w <= subscriber.getNumWorkers(); w++) {
            completionService.submit(new Worker(subscriber));
            count.incrementAndGet();
            int qtt = allocationMap.get(subscriber);
            qtt++;
            allocationMap.put(subscriber, qtt);
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Subscribers", subscribers)
                .toString();
    }

}

package com.github.jmetzz.laboratory.concurrency.standalone._99_demo.poolAcknowledge;

import com.github.jmetzz.laboratory.concurrency.standalone._99_demo.poolAcknowledge.handler.HandlerImpl;
import com.github.jmetzz.laboratory.concurrency.standalone._99_demo.poolAcknowledge.handler.IHandler;
import com.github.jmetzz.laboratory.concurrency.standalone._99_demo.poolAcknowledge.scheduler.Scheduler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by Jean Metz.
 */
public class AppLauncher {

    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        CompletionService<Subscribers.Subscriber> completionService = new ExecutorCompletionService<Subscribers.Subscriber>(executorService);
        IHandler handler = null;
        try {
            URL jsonUrl = new File("concurrency/src/main/resources/subscribers.json").toURI().toURL();
            handler = new HandlerImpl(completionService, new Subscribers(jsonUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // just simulates the timer service every 5 seconds
        Scheduler scheduler = new Scheduler(handler, 5000, 3);
        Thread newThread = new Thread(scheduler);
        newThread.start();

        // Make sure executor stops
        executorService.shutdown();

        // Blocks until all tasks have completed execution after a shutdown request
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);


    }
}

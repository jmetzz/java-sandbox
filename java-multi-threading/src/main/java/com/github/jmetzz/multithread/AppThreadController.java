package com.github.jmetzz.multithread;

import java.util.Random;

/**
 * Created by Jean Metz
 */
public class AppThreadController {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main thread general info: ");
        System.out.println("\tid = " + Thread.currentThread().getId());
        System.out.println("\tname = " + Thread.currentThread().getName());
        System.out.println("\tpriority = " + Thread.currentThread().getPriority());
        System.out.println("\tstate = " + Thread.currentThread().getState());
        System.out.println("\tthreadGroupName = " + Thread.currentThread().getThreadGroup().getName());

        runExample1();

        runExample2();

        interruptExample();

        joinExample();

        notSynchronizedCounterExample();

        synchronizedCounterExample();


        // TODO  Atomic Access example
    }

    private static void runExample1() {
        System.out.println(" --------Example 1---------- ");
        System.out.println("Starting a simple Runnable task...");
        Thread newThread = new Thread(new SimpleRunnable(1L), "My simple runnable");
        newThread.start();
        System.out.println("simple runnable task executed.");
    }

    private static void runExample2() {
        System.out.println(" --------Example 2---------- ");
        System.out.println("Starting a simple Runnable task...");
        Thread newThread = new Thread(new SleepingRunnable(), "My sleeping runnable");
        newThread.start();
        System.out.println("sleeping runnable task executed.");
    }

    public static void interruptExample() throws InterruptedException {
        System.out.println(" --------Interrupt Example---------- ");
        Thread myThread = new Thread(new InterruptedRunnable(), "myThread");
        myThread.start();

        System.out.println("[" + Thread.currentThread().getName() + "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);

        System.out.println("[" + Thread.currentThread().getName() + "] Interrupting myThread");
        myThread.interrupt();

        System.out.println("[" + Thread.currentThread().getName() + "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);

        System.out.println("[" + Thread.currentThread().getName() + "] Interrupting myThread");
        myThread.interrupt();
    }

    public static void joinExample() throws InterruptedException {
        System.out.println(" --------Join Example---------- ");
        Thread[] threads = new Thread[5];
        System.out.println("\t scheduling ... ");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new SimpleRunnable((new Random(System.currentTimeMillis())).nextInt(100) * 1000000L), "joinThread-" + i);
            threads[i].start();
        }

        System.out.println("\t joining threads ... ");
        for (int i = 0; i < threads.length; i++) {
            System.out.println("\t[" + threads[i].getName() + "] is Done?");
            threads[i].join();
            System.out.println("\t[" + threads[i].getName() + "] done!");
        }

        System.out.println("[" + Thread.currentThread().getName() + "] All threads have finished.");

        /*
        One possible result (actually got on one of my executions):
        --------Join Example----------
	    scheduling ...
        [joinThread-0] started.
        [joinThread-1] started.
        [joinThread-2] started.
             joining threads ...
            [joinThread-0] is Done?
        [joinThread-4] started.
        [joinThread-3] started.
        [joinThread-2] finished.
        [joinThread-4] finished.
        [joinThread-1] finished.
        [joinThread-3] finished.
        [joinThread-0] finished.
            [joinThread-0] done!
            [joinThread-1] is Done?
            [joinThread-1] done!
            [joinThread-2] is Done?
            [joinThread-2] done!
            [joinThread-3] is Done?
            [joinThread-3] done!
            [joinThread-4] is Done?
            [joinThread-4] done!
        [main] All threads have finished.
         ------------------
         */
    }

    public static void notSynchronizedCounterExample() throws InterruptedException {
        System.out.println(" --------Not synchronized counter Example---------- ");
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new NotSynchronizedCounter(), "thread-" + i);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }

    public static void synchronizedCounterExample() throws InterruptedException {
        System.out.println(" --------Synchronized counter Example---------- ");
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new SynchronizedCounter(), "thread-" + i);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

    }
}

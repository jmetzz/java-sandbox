package com.github.jmetzz.concurrency.standalone.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Jean Metz.
 */
public class FutureDemo {
    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

    }

    private static class Factorial implements Callable<Long> {
        private static int base;

        public Factorial(int base) {
            Factorial.base = base;
        }

        @Override
        public Long call() throws Exception {
            long output = 0L;

            try {
                output = factorial(base);
            } catch (InterruptedException e) {
                //
            }
            return output;
        }

        private long factorial(int base) throws InterruptedException {
            checkArgument(base > 0, "Base must be a positive integer greater than zero.");
            Thread.sleep(1);
            // do something ...
            return 0L;

        }


    }

}

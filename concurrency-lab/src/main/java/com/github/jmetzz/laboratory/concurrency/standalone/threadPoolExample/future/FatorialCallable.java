package com.github.jmetzz.laboratory.concurrency.standalone.threadPoolExample.future;

import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Jean Metz.
 */
public class FatorialCallable {

    public static void main(String[] args) {

        Random random = new Random(System.currentTimeMillis());
        List<Future<Long>> results = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            Callable<Long> task = new Factorial( 1 + Math.abs(random.nextInt(5)));
            Future<Long> future = executor.submit(task);
            results.add(future);
        }

        results.forEach((f) -> {
            try {
                /* throws TimeoutException if the thread doesn't finish in the specified time*/
                System.out.println(f.get(3, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}

class Factorial implements Callable<Long> {
    private int base;

    public Factorial(int base) {
        checkArgument(base > 0, "Base must be a positive integer greater than zero.");
        this.base = base;
    }

    @Override
    public Long call() throws Exception {
        BigInteger factorial = BigIntegerMath.factorial(base);
        return factorial.longValueExact();
    }

}



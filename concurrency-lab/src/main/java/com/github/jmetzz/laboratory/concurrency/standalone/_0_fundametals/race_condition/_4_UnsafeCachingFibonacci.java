package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


import com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.annotation.NotThreadSafe;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Careful, this simple cache is not atomic. Do not use this approach,
 * it is here for illustrations purposes only, not production code in
 * multi-thread applications, since there are much better options.
 */
@NotThreadSafe
public class _4_UnsafeCachingFibonacci {

    /**
     * Caches the last input given to request the fibonacci number
     */
    private final AtomicLong lastInput = new AtomicLong();

    /**
     * Caches the last calculated fibonacci value
     */
    private final AtomicLong lastResult = new AtomicLong();

    public long fibonacci(long n) {
        if (lastInput.get() == n) {
            // return the cached value
            return lastResult.get();
        } else {
            // calculate a new value and cache it
            long f = Fibonacci.evaluate(n);

            /*
             * Observe that the next two operations are indeed individually atomic
             * However, the whole caching operations is not, which makes the class
             * non thread safe since the two fields are not independent in terms
             * of thread correctness.
             * Thus when updating one, you must update the other in the
             * same atomic operation
             * */
            lastInput.set(n);
            lastResult.set(f);
            return f;
        }
    }
}

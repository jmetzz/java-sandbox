package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


import com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.annotation.ThreadSafe;

/**
 * This simple caching strategy is atomic. However, it is only for illustration purposes.
 * Don't use it in production since it inhibits multiple clients from using the class
 * simultaneously at all resulting in unacceptably poor responsiveness.
 * There are much better options.
 */
@ThreadSafe
public class _5_SafeCachingFibonacci {

    /**
     * Caches the last input given to request the fibonacci number
     */
    private Long lastInput = null;

    /**
     * Caches the last calculated fibonacci value
     */
    private Long lastResult = null;


    /**
     * only one thread may enter this method at a time
     * @param n
     */
    public synchronized long fibonacci(long n) {
         /*
             * Due to the synchronized block (the whole method, in thi case)
             * with the lock being the caller object (this),
             * this method is executed as an atomic operation.
             * However, the concurrency performance using this approach
             * is very poor.
             * */

        if (lastInput != null && lastInput == n) {
            return lastResult;
        } else {
            long f = Fibonacci.evaluate(n);
            /**
             * Atomic variables are useful for effecting atomic operations on a single variable,
             * but since we are already using synchronized blocks to construct atomic operations,
             * using two different synchronization mechanisms would be confusing and would offer
             * no performance or safety benefit.
             * */
            lastInput = n;
            lastResult = f;
            return f;
        }
    }
}

package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


import com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.annotation.ThreadSafe;

/**
 * This simple caching strategy is atomic. Even though it is a better
 * solution than the previous one, it is still only for illustration purposes.
 * This solution restructures the service method fibonacci to use two separate
 * synchronized blocks, each limiting a short section of code.
 * One guards the check-then-act sequence that tests whether we can
 * just return the cached result, and the other guards updating both
 * the cached number and the cached factors. This improves the performance
 * while maintaining thread safety by narrowing the scope of the synchronized block.
 * Just be careful not to make the scope of the synchronized block too small and break
 * the atomicity of important block of operations that should behave like one.
 */
@ThreadSafe
public class _6_SafeAndFasterCachingFibonacci {

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
     *
     * @param n
     */
    public long fibonacci(long n) {
        long result = -1;

        /**
         * Now this method is implemented with the strategy
         * "synchronize the shortest possible code paths"
         * in mind, so you get thread safety you want and
         * a better performance when compared with synchronizing
         * the whole block, which would (in this particular case)
         * result in almost "serial" execution even with many
         * processor resources available
         */

        /**
         * holds the lock when accessing state variables and
         * for the duration of compound actions, but releases it
         * before executing the potentially long-running operation.
         * This preserves thread safety without affecting concurrency
        */
        synchronized (this) {
            if (lastInput != null && lastInput == n)
                result = lastResult;
        }

        if (result > -1) {
            result = Fibonacci.evaluate(n);
            synchronized (this) {
                lastInput = n;
                lastResult = result;
            }
        }
        return result;
    }
}

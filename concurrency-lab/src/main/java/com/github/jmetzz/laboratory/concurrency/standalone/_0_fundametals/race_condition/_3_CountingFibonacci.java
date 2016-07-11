package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


import com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class _3_CountingFibonacci {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount(){return count.get();}

    public long fibonacci(long n){
        long f = Fibonacci.evaluate(n);
        count.incrementAndGet();
        return f;
    }

}

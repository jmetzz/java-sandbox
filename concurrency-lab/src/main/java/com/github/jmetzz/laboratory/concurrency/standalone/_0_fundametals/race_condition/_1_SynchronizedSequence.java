package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;

import com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.annotation.ThreadSafe;

@ThreadSafe
public class _1_SynchronizedSequence {

    private int value = 0;

    /** Returns a unique value */
    public synchronized int getNext(){
        /*
        * this method is thread safe due to the
        * synchronized keyword, which blocks any concurrent access
        * to this block of code (race condition).
        * For a multithreaded program’s behavior to be predictable,
        * access to shared variables must be properly coordinated
        * so that threads do not interfere with one another.
        * */
        return value++;
    }


}

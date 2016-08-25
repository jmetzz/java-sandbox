package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;

import com.github.jmetzz.laboratory.concurrency.standalone.annotation.NotThreadSafe;


/**
 * When this object is used by different threads,
 * it might result in race condition issues
 */
@NotThreadSafe
public class _0_UnsafeSequence {

    private int value = 0;

    /** Returns a unique value. */
    public int getNext(){
        /* Besides the method specification, this method
         * will not always return unique values due to
         * race conditions, since neither the method nor
         * the field are protected against this hazard.
         * In fact, because threads share the same
         * memory address space and run concurrently,
         * they can access or modify variables that
         * other threads might be using */
        return value++;
    }
}

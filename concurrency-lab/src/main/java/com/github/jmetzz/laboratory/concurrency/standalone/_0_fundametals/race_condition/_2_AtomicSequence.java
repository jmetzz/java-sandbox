package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


import com.github.jmetzz.laboratory.concurrency.standalone.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The java.util.concurrent.atomic package contains atomic variable classes
 * for effecting atomic state transitions on numbers and object references.
 * By using theses classes we ensure that all actions that access the value
 * field state are atomic, therefore ensuring thread safety on this class.
 */
@ThreadSafe
public class _2_AtomicSequence {

    private final AtomicLong value = new AtomicLong(0);

    /** Returns a unique value */
    public long getNext(){
        return value.incrementAndGet();
    }

}

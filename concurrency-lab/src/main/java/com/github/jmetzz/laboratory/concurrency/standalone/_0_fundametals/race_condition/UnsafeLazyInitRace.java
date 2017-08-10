package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;

import com.github.jmetzz.laboratory.concurrency.standalone.annotation.NotThreadSafe;

/**
 * Singleton patterns that is not thread safe.
 * Do not use this kind of lazy instantiation in
 * multi-threaded applications
 */
@NotThreadSafe
public class UnsafeLazyInitRace {

    private UnsafeLazyInitRace instance = null;

    private UnsafeLazyInitRace() {
        // keep it private to protect object instantiation
    }

    public UnsafeLazyInitRace getInstance() {
        if (instance == null)
            instance = new UnsafeLazyInitRace();
        return instance;
    }
}

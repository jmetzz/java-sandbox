package com.github.jmetzz.laboratory.design_patterns.creational.singleton;

/**
 * To avoid the extra overhead caused by synchonized method
 * we can employ a double checked locking principle. In this
 * approach, the synchronized block is used inside if condition
 * with an additional check to ensure that only one instance
 * of singleton class is created.
 */
public class _5_DoubleLockingSingleton {


    /**
     * It's easy to forget the volatile statement and difficult to understand why it is necessary.
     * For details : http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
     */
    private static volatile _5_DoubleLockingSingleton instance;

    private _5_DoubleLockingSingleton() {}

    public static synchronized _5_DoubleLockingSingleton getInstance() {

        if (instance == null) {
            synchronized ((_5_DoubleLockingSingleton.class)) {
                if (instance == null) {
                    instance = new _5_DoubleLockingSingleton();
                }
            }
        }

        return instance;
    }
}

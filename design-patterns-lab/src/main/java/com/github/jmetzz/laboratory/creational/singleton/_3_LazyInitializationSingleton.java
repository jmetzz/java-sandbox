package com.github.jmetzz.laboratory.creational.singleton;

/**
 * Works fine in single thread scenarios.
 */
public class _3_LazyInitializationSingleton {

    private static _3_LazyInitializationSingleton instance;

    private _3_LazyInitializationSingleton() {
    }

    public static _3_LazyInitializationSingleton getInstance() {
        if (instance == null) {
            instance = new _3_LazyInitializationSingleton();
        }
        return instance;
    }

}

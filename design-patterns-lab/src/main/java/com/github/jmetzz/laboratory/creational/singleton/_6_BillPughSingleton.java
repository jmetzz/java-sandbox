package com.github.jmetzz.laboratory.creational.singleton;

/**
 * Singleton class using an inner static helper class
 */
public class _6_BillPughSingleton {

    private _6_BillPughSingleton instance;

    /*
    * When the singleton class is loaded, SingletonHolder class is
    * not loaded into memory and only when someone calls the getInstance
    * method, this class gets loaded and creates the Singleton class instance.
    * it doesn’t require synchronization !!!
     */
    private static class SingletonHolder {
        private static final _6_BillPughSingleton INSTANCE = new _6_BillPughSingleton();
    }

    public static _6_BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

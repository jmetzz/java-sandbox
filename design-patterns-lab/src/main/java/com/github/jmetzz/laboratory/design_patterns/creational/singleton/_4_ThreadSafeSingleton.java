package com.github.jmetzz.laboratory.design_patterns.creational.singleton;

/**
 * works fine and provides thread-safety but it reduces
 * the performance because of cost associated with the synchronized method
 */
public class _4_ThreadSafeSingleton {

    private static _4_ThreadSafeSingleton instance;

    private _4_ThreadSafeSingleton(){}

    public static synchronized _4_ThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new _4_ThreadSafeSingleton();
        }
        return instance;
    }
}

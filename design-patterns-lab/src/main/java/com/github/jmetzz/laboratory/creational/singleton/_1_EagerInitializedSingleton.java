package com.github.jmetzz.laboratory.creational.singleton;


public class _1_EagerInitializedSingleton {

    private static final _1_EagerInitializedSingleton instance = new _1_EagerInitializedSingleton();

    private _1_EagerInitializedSingleton(){
        // protect the creation method against client calls
        // Beware of reflection enemies :p
    }

    public static _1_EagerInitializedSingleton getInstance(){
        return  instance;
    }


}

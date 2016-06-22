package com.github.jmetzz.laboratory.creational.singleton.serialization;


import java.io.Serializable;

public class CorrectSerializedSingleton implements Serializable {

    private static final long serialVersionUID = 971266441583519862L;

    private CorrectSerializedSingleton() {
    }

    private static class SingletonHolder {
        private static final CorrectSerializedSingleton instance = new CorrectSerializedSingleton();
    }

    public static CorrectSerializedSingleton getInstance() {
        return SingletonHolder.instance;
    }

    protected Object readResolve(){
        return getInstance();
    }
}

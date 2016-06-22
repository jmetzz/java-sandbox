package com.github.jmetzz.laboratory.creational.singleton.serialization;


import java.io.Serializable;

public class ProblematicSerializedSingleton implements Serializable {

    private static final long serialVersionUID = 971266441583519862L;

    private ProblematicSerializedSingleton() {
    }

    private static class SingletonHolder {
        private static final ProblematicSerializedSingleton instance = new ProblematicSerializedSingleton();
    }

    public static ProblematicSerializedSingleton getInstance() {
        return SingletonHolder.instance;
    }

}

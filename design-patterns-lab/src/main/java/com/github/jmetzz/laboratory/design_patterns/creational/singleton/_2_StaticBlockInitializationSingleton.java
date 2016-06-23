package com.github.jmetzz.laboratory.design_patterns.creational.singleton;


public class _2_StaticBlockInitializationSingleton {

    private static _2_StaticBlockInitializationSingleton instance;

    static {
        try {
            instance = new _2_StaticBlockInitializationSingleton();
        } catch(Exception e){
            throw new RuntimeException("Exception while creating singleton instance");
        }
    }

    private _2_StaticBlockInitializationSingleton(){
        // protect the creation method against client calls
        // Beware of reflection enemies :p
    }

    public static _2_StaticBlockInitializationSingleton getInstance(){
        return instance;
    }
}

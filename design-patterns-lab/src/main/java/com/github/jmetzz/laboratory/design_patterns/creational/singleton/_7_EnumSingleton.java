package com.github.jmetzz.laboratory.design_patterns.creational.singleton;

/**
 * Joshua Bloch suggests the use of Enum to implement
 * Singleton design pattern as Java ensures that any enum
 * value is instantiated only once in a Java program.
 * This strategy overcomes the problems with reflection.
 * However, we loose lazy initialization capability.
 */
public enum _7_EnumSingleton {
    INSTANCE;

    public static void businessMethod(){
        // do your business here ...
    }
}

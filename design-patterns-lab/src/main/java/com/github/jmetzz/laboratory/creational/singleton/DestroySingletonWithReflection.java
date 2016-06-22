package com.github.jmetzz.laboratory.creational.singleton;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DestroySingletonWithReflection {

    public static void main(String[] args) {
        _1_EagerInitializedSingleton instance1 = _1_EagerInitializedSingleton.getInstance();
        _1_EagerInitializedSingleton instance2 = null;
        System.out.println("EagerInitializedSingleton Instance 1: " + instance1.hashCode());

        instance2 = getEagerInitializedSingletonByReflection();
        System.out.println("EagerInitializedSingleton Instance 2: " + instance2.hashCode());


        _7_EnumSingleton enumSingleton1 = _7_EnumSingleton.INSTANCE;
        System.out.println("EnumSingleton Instance 1: " + enumSingleton1.hashCode());

        _7_EnumSingleton enumSingleton2 = getEnumSingletonByReflection();

        if(enumSingleton2 != null)
            System.out.println("EnumSingleton Instance 2: " + enumSingleton2.hashCode());

    }

    private static _1_EagerInitializedSingleton getEagerInitializedSingletonByReflection() {
        _1_EagerInitializedSingleton instance = null;
        try {
            Constructor[] constructors = _1_EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instance = (_1_EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println("Failed to create a second instance of an _1_EagerInitializedSingleton. Cause: ");
            System.out.println(e.getMessage());
        }
        return instance;
    }

    private static _7_EnumSingleton getEnumSingletonByReflection() {
        _7_EnumSingleton instance = null;
        try {
            Constructor[] constructors = _7_EnumSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instance = (_7_EnumSingleton) constructor.newInstance();
                break;
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | IllegalArgumentException e) {
            System.out.println("Failed to create a second instance of an EnumSingleton. Cause: ");
            System.out.println(e.getMessage());
        }
        return instance;
    }
}

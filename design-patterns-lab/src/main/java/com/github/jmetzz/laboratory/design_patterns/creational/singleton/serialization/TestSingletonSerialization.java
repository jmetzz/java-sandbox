package com.github.jmetzz.laboratory.design_patterns.creational.singleton.serialization;


import java.io.*;

/**
 * Serialization is yet another way your singleton can be broken :(
 * because every time the object is deserialized, a new instance will
 * be created.
 * Solution: provide the implementation of readResolve() method.
 */
public class TestSingletonSerialization {

    public static final String P_SERIALIZED_SINGLETON_FILENAME = "p_serializedSingleton.ser";
    public static final String C_SERIALIZED_SINGLETON_FILENAME = "c_serializedSingleton.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ProblematicSerializedSingleton pss1 = ProblematicSerializedSingleton.getInstance();

        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(P_SERIALIZED_SINGLETON_FILENAME));
        out.writeObject(pss1);
        out.close();


        ObjectInput in = new ObjectInputStream(new FileInputStream(P_SERIALIZED_SINGLETON_FILENAME));
        ProblematicSerializedSingleton pss2 = (ProblematicSerializedSingleton) in.readObject();
        in.close();

        System.out.println("ProblematicSerializedSingleton 1: " + pss1.hashCode());
        System.out.println("ProblematicSerializedSingleton 2: " + pss2.hashCode());

        System.out.println("--------------------");


        CorrectSerializedSingleton css1 = CorrectSerializedSingleton.getInstance();

        out = new ObjectOutputStream(new FileOutputStream(P_SERIALIZED_SINGLETON_FILENAME));
        out.writeObject(css1);
        out.close();


        in = new ObjectInputStream(new FileInputStream(P_SERIALIZED_SINGLETON_FILENAME));
        CorrectSerializedSingleton css2 = (CorrectSerializedSingleton) in.readObject();
        in.close();

        System.out.println("CorrectSerializedSingleton 1: " + css1.hashCode());
        System.out.println("CorrectSerializedSingleton 2: " + css2.hashCode());

    }

}

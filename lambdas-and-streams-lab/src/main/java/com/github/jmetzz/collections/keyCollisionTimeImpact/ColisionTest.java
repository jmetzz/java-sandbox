package com.github.jmetzz.collections.keyCollisionTimeImpact;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ColisionTest {

    private static final int LIMIT = 50000;

    public static void main(String[] args) {
        fillAndSearchWithPerson();
        fillAndSearchWithComparablePerson();
    }

    private static void fillAndSearchWithPerson() {
        Person person = null;
        Map<Person, String> map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < LIMIT; i++) {
            UUID randomUUID = UUID.randomUUID();
            person = new Person("firstName", "lastName", randomUUID);
            map.put(person, "comment" + i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Filling time: " + (stop - start) + " millis");

        start = System.currentTimeMillis();
        map.get(person);
        stop = System.currentTimeMillis();
        System.out.println("Query time: " + (stop - start) + " millis");
    }

    private static void fillAndSearchWithComparablePerson() {
        ComparablePerson person = null;
        Map<ComparablePerson, String> map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < LIMIT; i++) {
            UUID randomUUID = UUID.randomUUID();
            person = new ComparablePerson("firstName", "lastName", randomUUID);
            map.put(person, "comment" + i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Filling time: " + (stop - start) + " millis");

        start = System.currentTimeMillis();
        map.get(person);
        stop = System.currentTimeMillis();
        System.out.println("Query time: " + (stop - start) + " millis");
    }


}

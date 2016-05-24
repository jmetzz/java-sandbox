package com.github.jmetzz.collections.keyCollisionTimeImpact;

import java.util.UUID;

public class ComparablePerson implements  Comparable<ComparablePerson> {

    private final String firstName;

    private final String lastName;

    private final UUID id;

    public ComparablePerson(String firstName, String lastName, UUID id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        // returning always the same value on purpose to generate collisions
        return 1;
    }

    public boolean equals(Person other) {
        return this.id.equals(other.getId());
    }

    public int compareTo(ComparablePerson other){
        return this.getId().compareTo(other.getId());
    }
}

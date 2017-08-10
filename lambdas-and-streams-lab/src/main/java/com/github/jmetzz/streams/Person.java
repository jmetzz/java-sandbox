package com.github.jmetzz.streams;

public class Person {

    private final String firstName;

    private final String lastName;

    private final Gender gender;

    private final int age;

    public Person(String name, String lastName, Gender gender, int age) {
        this.firstName = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("First Name", firstName)
                .add("Last Name", lastName)
                .add("Gender", gender)
                .add("Age", age)
                .toString();
    }


}

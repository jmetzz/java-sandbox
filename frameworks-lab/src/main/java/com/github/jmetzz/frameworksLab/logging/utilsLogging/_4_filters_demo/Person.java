package com.github.jmetzz.frameworksLab.logging.utilsLogging._4_filters_demo;


public class Person {

    private String name = null;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .toString();
    }
}

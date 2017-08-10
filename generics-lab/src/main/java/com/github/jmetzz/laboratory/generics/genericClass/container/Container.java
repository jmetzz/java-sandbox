package com.github.jmetzz.laboratory.generics.genericClass.container;

public interface Container<T> {

    void put(T content);

    T get();

    double volume();

    double width();

    double length();

    double height();

}

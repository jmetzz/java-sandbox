package com.github.jmetzz.genericClass.container;

public interface Container<T> {

    void put(T content);

    T get();

    double volume();

    double width();

    double length();

    double height();

}

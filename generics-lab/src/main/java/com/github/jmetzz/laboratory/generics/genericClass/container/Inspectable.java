package com.github.jmetzz.laboratory.generics.genericClass.container;

public interface Inspectable<T> {

    <U extends T> void inspect(U u);
}

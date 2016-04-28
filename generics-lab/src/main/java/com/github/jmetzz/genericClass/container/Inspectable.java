package com.github.jmetzz.genericClass.container;

public interface Inspectable<T> {

    <U extends T> void inspect(U u);
}

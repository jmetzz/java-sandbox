package com.github.jmetzz.laboratory.generics.genericClass.tuple;

public interface HeterogeneousPair<K, V> {
    K getLeft();
    V getRight();
    void setLeft(K value);
    void setRight(V value);
}

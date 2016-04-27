package com.github.jmetzz.genericClass.tuple;

public class AnyTypePair<K, V> implements HeterogeneousPair<K, V> {

    private K left;

    private V right;


    @Override
    public K getLeft() {
        return left;
    }

    @Override
    public V getRight() {
        return right;
    }

    @Override
    public void setLeft(K value) {
        this.left = value;
    }

    @Override
    public void setRight(V value) {
        this.right = value;
    }
}

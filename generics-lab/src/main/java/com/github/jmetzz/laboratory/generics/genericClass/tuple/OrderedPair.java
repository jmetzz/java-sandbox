package com.github.jmetzz.laboratory.generics.genericClass.tuple;

public class OrderedPair<K, V> implements HeterogeneousPair<K, V> {

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

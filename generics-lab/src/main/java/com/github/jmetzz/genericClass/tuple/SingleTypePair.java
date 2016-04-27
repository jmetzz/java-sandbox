package com.github.jmetzz.genericClass.tuple;

import com.google.common.base.MoreObjects;

/**
 * Created by Jean Metz.
 */
public class SingleTypePair<T> implements HomogeneousPair<T> {

    private T left;
    private T right;

    public SingleTypePair() {
    }

    public SingleTypePair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("left", left)
                .add("right", right)
                .toString();
    }

}

package com.github.jmetzz.generics;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created by Jean Metz.
 */
public class BoundedPair<T extends Comparable & Serializable> {

    private T left;
    private T right;


    public BoundedPair(T left, T right) {
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

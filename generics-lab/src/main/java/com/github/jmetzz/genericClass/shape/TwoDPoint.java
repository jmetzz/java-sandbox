package com.github.jmetzz.genericClass.shape;

import com.github.jmetzz.genericClass.tuple.HomogeneousPair;
import com.github.jmetzz.genericClass.tuple.SingleTypePair;

/**
 * Created by Jean Metz.
 */
public class TwoDPoint<T extends Number> implements Comparable<TwoDPoint> {


    private HomogeneousPair<T> coordinate;

    public TwoDPoint() {
        coordinate = new SingleTypePair<T>();
    }

    public TwoDPoint(SingleTypePair<T> c) {
        this.coordinate = c;
    }

    public T getX() {
        return coordinate.getLeft();
    }

    public void setX(T x) {
        this.coordinate.setLeft(x);
    }

    public T getY() {
        return coordinate.getRight();
    }

    public void setY(T y) {
        this.coordinate.setRight(y);
    }


    public Double norm() {
        double x = getX().doubleValue();
        double y = getY().doubleValue();

        return Math.sqrt(x * x + y * y);
    }

    @Override
    public int compareTo(TwoDPoint o) {
        return this.norm().compareTo(o.norm());
    }
}

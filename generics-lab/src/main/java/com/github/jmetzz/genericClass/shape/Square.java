package com.github.jmetzz.genericClass.shape;

import com.github.jmetzz.genericClass.tuple.SingleTypePair;

public class Square implements Shape{

    private Rectangle rectangle; // using composition instead of specialization :)

    public Square(TwoDPoint<Double> ref, double size) {
        TwoDPoint<Double> p2 = new TwoDPoint<>(new SingleTypePair<>(ref.getX().doubleValue() + size, ref.getY().doubleValue() + size));
        this.rectangle = new Rectangle(ref, p2);
    }

    public TwoDPoint<Double> getP1() {
        return rectangle.getP1();
    }

    public void setP1(TwoDPoint<Double> p1) {
        rectangle.setP1(p1);
    }

    public TwoDPoint<Double> getP2() {
        return rectangle.getP2();
    }

    public void setP2(TwoDPoint<Double> p2) {
        rectangle.setP2(p2);
    }

    @Override
    public void draw() {
        System.out.println(Square.class.getSimpleName());
        System.out.println(rectangle.getP1() + " - " + rectangle.getP2());
    }
}
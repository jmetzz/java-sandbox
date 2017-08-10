package com.github.jmetzz.laboratory.generics.genericClass.shape;

public class Rectangle implements Shape{

    private TwoDPoint<Double> p1;
    private TwoDPoint<Double> p2;

    public  Rectangle(TwoDPoint<Double> p1, TwoDPoint<Double> p2){
        this.p1 = p1;
        this.p2 = p2;
    }


    public TwoDPoint<Double> getP1() {
        return p1;
    }

    public void setP1(TwoDPoint<Double> p1) {
        this.p1 = p1;
    }

    public TwoDPoint<Double> getP2() {
        return p2;
    }

    public void setP2(TwoDPoint<Double> p2) {
        this.p2 = p2;
    }

    @Override
    public void draw() {
        System.out.println(Rectangle.class.getSimpleName());
        System.out.println(p1 + " - " + p2);
    }
}

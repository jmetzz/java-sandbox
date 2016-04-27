package com.github.jmetzz.genericClass.shape;

public class Circle implements Shape {

    private TwoDPoint<Double> center;

    private double radius;

    public Circle(TwoDPoint<Double> center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public TwoDPoint<Double> getCenter() {
        return center;
    }

    public void setCenter(TwoDPoint<Double> center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println(Circle.class.getSimpleName());
        System.out.printf(center + " r: " + radius);
    }
}

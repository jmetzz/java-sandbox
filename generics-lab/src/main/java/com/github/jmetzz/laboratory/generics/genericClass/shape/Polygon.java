package com.github.jmetzz.laboratory.generics.genericClass.shape;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Polygon implements Shape{

    private Set<TwoDPoint<Double>> pointSet;

    public Polygon(TwoDPoint<Double> ... points){
        this(Arrays.asList(points));
    }

    public Polygon (List<TwoDPoint<Double>> pointList){
        Preconditions.checkArgument(pointList.size() >= 3, "Should inform at least 3 points to build a polygon");

        this.pointSet = new HashSet<>(pointList);
    }

    public void addPoint(TwoDPoint<Double> p){
        Preconditions.checkNotNull(p);
        pointSet.add(p);
    }

    public void removePoint(TwoDPoint<Double> p){
        Preconditions.checkNotNull(p);
        Preconditions.checkState(pointSet.size() > 3, "Removing element would result in a malformed polygon with less than 3 points");
        pointSet.remove(p);
    }


    @Override
    public void draw() {
        System.out.printf(Polygon.class.getSimpleName());
        System.out.println(pointSet);
    }



}

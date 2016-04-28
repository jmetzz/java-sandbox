package com.github.jmetzz.genericClass.container;

public class NumericBox<T extends Number> extends GenericBox<T> {

    public NumericBox(double width, double lenght, double height, String build) {
        super(width, lenght, height, build);
    }

    @Override
    public <U extends T> void inspect(U u) {
        System.out.println("T: " + get().getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

}

package com.github.jmetzz.genericClass.container;

public class IntegerBox<T extends Integer> extends AbstractBox<T> {

    public IntegerBox(double width, double lenght, double height, String build) {
        super(width, lenght, height, build);
    }

    /* bounded type parameter example */
    public <U extends Number> void instpect (U u){
        System.out.println("T: " + get().getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

}

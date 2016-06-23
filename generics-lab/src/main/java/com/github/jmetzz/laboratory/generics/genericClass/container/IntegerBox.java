package com.github.jmetzz.laboratory.generics.genericClass.container;

public class IntegerBox<T extends Integer> extends GenericBox<T> {

    public IntegerBox(double width, double lenght, double height, String build) {
        super(width, lenght, height, build);
    }

    @Override
    public <U extends T> void inspect(U u) {
        System.out.println("T: " + get().getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }


}

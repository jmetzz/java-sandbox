package com.github.jmetzz.genericClass.container;

import com.google.common.base.MoreObjects;

public abstract class AbstractBox<T> implements Container<T> {

    private final double width;
    private final double lenght;
    private final double height;
    private final String build;

    private T content;

    public AbstractBox(double width, double lenght, double height, String build) {
        this.width = width;
        this.lenght = lenght;
        this.height = height;
        this.build = build;
    }

    @Override
    public T get() {
        return content;
    }

    @Override
    public void put(T content) {
        this.content = content;
    }

    @Override
    public double volume() {
        return width * height * lenght;
    }

    @Override
    public double width() {
        return this.width;
    }

    @Override
    public double lenght() {
        return this.lenght;
    }

    @Override
    public double height() {
        return this.lenght;
    }

    public String build() {
        return build;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("width", width)
                .add("lenght", lenght)
                .add("height", height)
                .add("build", build)
                .add("content", content)
                .toString();
    }
}

package com.github.jmetzz.laboratory.creational.builder;

public abstract class AbstractBuilder<T> {
    private T instance;
    private boolean isBuilt = false;

    protected AbstractBuilder(T instance) {
        this.instance = instance;
    }

    public T build() {
        this.assertNotBuilt();
        this.beforeBuild();
        this.isBuilt = true;
        return this.instance;
    }

    protected void beforeBuild() {
    }

    protected T getInstance() {
        this.assertNotBuilt();
        return this.instance;
    }

    protected void assertNotBuilt() {
        if(this.isBuilt) {
            throw new IllegalStateException("Instance is already built");
        }
    }
}

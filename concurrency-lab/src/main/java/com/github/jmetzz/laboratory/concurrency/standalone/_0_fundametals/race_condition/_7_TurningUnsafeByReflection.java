package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.race_condition;


import com.google.common.base.MoreObjects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * This example shows how to break the encapsulation using java Reflection.
 * Reflection is a very powerful tool, but you have to use it with care,
 * since changing object's value/properties can lead to serious concurrency problems.
 * Imagine that ImmutablePoint instances were used in concurrent applications
 * designed based on immutability and intrinsic lock, changing the visibility or
 * final modifier will break completely the design and thread safety.
 */
public class _7_TurningUnsafeByReflection {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        ImmutablePoint point = new ImmutablePoint(2, 6);
        System.out.println("Before modification via reflection: " + point);

        modifyFinalField(point, ImmutablePoint.class.getField("y"), 1);

        System.out.println("After modification via reflection: " + point);

    }

    private static void modifyFinalField(Object instance, Field toChange, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        toChange.setAccessible(true);
        // remove final modifier from field
        Field fieldModifiers = Field.class.getDeclaredField("modifiers");
        fieldModifiers.setAccessible(true);
        fieldModifiers.setInt(toChange, toChange.getModifiers() & ~Modifier.FINAL);
        toChange.set(instance, newValue);
    }

}


class ImmutablePoint {

    public final int x;
    public final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("x", x)
                .add("y", y)
                .toString();
    }
}
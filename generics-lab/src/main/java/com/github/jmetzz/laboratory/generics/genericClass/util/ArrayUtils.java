package com.github.jmetzz.laboratory.generics.genericClass.util;

import com.github.jmetzz.laboratory.generics.genericClass.tuple.SingleTypePair;

/**
 * Created by Jean Metz.
 *
 * Utility class used to illustrate the usage of Java Generics
 *
 */
public class ArrayUtils {

    public static <T extends Comparable> SingleTypePair<T> minmax1(T[] a) {
        if ((a == null) || (a.length == 0))
            return null;

        T min = a[0];
        T max = a[0];
        for (T element : a) {
            if (min.compareTo(element) > 0)
                min = element;

            if (max.compareTo(element) < 0)
                max = element;
        }
        return new SingleTypePair<T>(min, max);
    }

    public static <T extends Comparable<T>> SingleTypePair<T> minmax2(T[] a) {
        if ((a == null) || (a.length == 0))
            return null;

        T min = a[0];
        T max = a[0];
        for (T element : a) {
            if (min.compareTo(element) > 0)
                min = element;

            if (max.compareTo(element) < 0)
                max = element;
        }
        return new SingleTypePair<T>(min, max);
    }

    public static <T extends Comparable<? super T>> SingleTypePair<T> minmax3(T[] a) {
        if ((a == null) || (a.length == 0))
            return null;

        T min = a[0];
        T max = a[0];
        for (T element : a) {
            if (min.compareTo(element) > 0)
                min = element;

            if (max.compareTo(element) < 0)
                max = element;
        }
        return new SingleTypePair<T>(min, max);
    }

    /*
    You can even use wildcards with no bounds at all, for example, SingleTypePair<?>.
    At first glance, this looks identical to the raw SingleTypePair type.
    Actually, the types are very different. The type SingleTypePair<?> has methods such as:
    ? getLeft()
    void setLeft(?)
    The return value of getLeft can only be assigned to an Object. The setLeft method can
    never be called, not even with an Object.
    That’s the essential difference between SingleTypePair<?> and SingleTypePair:
        you can call the setObject method of the raw SingleTypePair class with any Object
    */
    public static SingleTypePair minmax0(Object[] a) {
        if ((a == null) || (a.length == 0))
            return null;

        Comparable min = (Comparable) a[0];
        Comparable max = (Comparable) a[0];
        for (int index = 1; index < a.length; ++index) {

            Comparable element = (Comparable) a[index];

            if (min.compareTo(element) > 0)
                min = element;

            if (max.compareTo(element) < 0)
                max = element;
        }
        return new SingleTypePair(min, max);
    }


}
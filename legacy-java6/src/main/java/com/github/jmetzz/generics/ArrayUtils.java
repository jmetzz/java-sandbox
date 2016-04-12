package com.github.jmetzz.generics;

/**
 * Created by Jean Metz.
 */
public class ArrayUtils {

    public static <T extends Comparable> Pair<T> minmax1(T[] a) {
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
        return new Pair<T>(min, max);
    }

    public static <T extends Comparable<T>> Pair<T> minmax2(T[] a) {
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
        return new Pair<T>(min, max);
    }

    public static <T extends Comparable<? super T>> Pair<T> minmax3(T[] a) {
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
        return new Pair<T>(min, max);
    }

    /*
    You can even use wildcards with no bounds at all, for example, Pair<?>.
    At first glance, this looks identical to the raw Pair type.
    Actually, the types are very different. The type Pair<?> has methods such as:
    ? getLeft()
    void setLeft(?)
    The return value of getLeft can only be assigned to an Object. The setLeft method can
    never be called, not even with an Object.
    That’s the essential difference between Pair<?> and Pair:
        you can call the setObject method of the raw Pair class with any Object
    */
    public static Pair minmax0(Object[] a) {
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
        return new Pair(min, max);
    }


}
package com.github.jmetzz.genericClass.util;

import com.github.jmetzz.genericClass.tuple.OrderedPair;

/**
 * Created by Jean Metz.
 *
 * Utility class used to illustrate the usage of Java Generics
 *
 */
public class PairUtil {

    public static <K, V> boolean compare(OrderedPair<K, V> p1, OrderedPair<K, V> p2) {
        return p1.getLeft().equals(p2.getLeft()) &&
                p1.getRight().equals(p2.getRight());
    }

}

package com.github.jmetzz.challenges.util;

import java.util.Collection;

/**
 * Created by Jean Metz.
 */
public class CollectionsHelper {

    public static Integer sum(Collection<Integer> collection) {
        Integer total = 0;
        for (Integer e : collection) {
            total = total + e;
        }
        return total;
    }

}

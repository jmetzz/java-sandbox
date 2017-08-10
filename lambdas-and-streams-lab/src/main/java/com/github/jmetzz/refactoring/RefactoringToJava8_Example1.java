package com.github.jmetzz.refactoring;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;

public class RefactoringToJava8_Example1 {
    private ConcurrentMap<String, Object> dataCache;

    // first and a bit verbose approach
    public void deleteFromCache_1_0(Set<String> deleteKeys) {
        Iterator<Map.Entry<String, Object>> iterator = dataCache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (deleteKeys.contains(entry.getKey())) {
                iterator.remove();
            }
        }
    }

    // a bit cleaner, but still non functional
    public void deleteFromCache_1_1(Set<String> deleteKeys) {
        for (String deleteKey : deleteKeys) {
            dataCache.remove(deleteKey);
        }
    }

    /*
    Java 8 introduced the removeIf method as a default method, not in Map but instead in the Collection interface.
    This new method "removes all of the elements of this collection that satisfy the given predicate".
    This method accepts one argument, a Predicate, which is a functional interface introduced in Java 8,
    which can therefore act as a lambda expression, a method reference, or a constructor reference
     */
    public void deleteFromCache_1_2(Set<String> deleteKeys) {
        dataCache.entrySet().removeIf(new Predicate<Map.Entry<String, Object>>() {
            @Override
            public boolean test(Map.Entry<String, Object> entry) {
                return deleteKeys.contains(entry.getKey());
            }
        });
    }

    /* First functional attempt, still a bit verbose.
     */
    public void deleteFromCache_2_0(Set<String> deleteKeys) {
        dataCache.entrySet().removeIf((Map.Entry<String, Object> entry) -> deleteKeys.contains(entry.getKey()));
    }

    /*
    Since Java 8 can infer the argument types of lambda expressions, we can remove the explicit type declaration
     */
    public void deleteFromCache_2_1(Set<String> deleteKeys) {
        dataCache.entrySet().removeIf(entry -> deleteKeys.contains(entry.getKey()));
    }
}

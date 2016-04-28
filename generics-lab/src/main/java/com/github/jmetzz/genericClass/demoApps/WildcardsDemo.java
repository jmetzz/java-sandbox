package com.github.jmetzz.genericClass.demoApps;

import java.util.List;

public class WildcardsDemo {

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }


}

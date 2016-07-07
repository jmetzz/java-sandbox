package com.github.jmetzz.laboratory.concurrency.standalone.threadPoolExample.executors;

import java.util.Map;

/**
 * Created by Jean Metz.
 */
public class MapModifier implements Runnable {

    private final Map<String, Integer> map;

    public MapModifier(Map<String, Integer> map) {
        this.map = map;
    }


    @Override
    public void run() {
        // do some update in the map structure
        for (int i = 0; i < 5000; i++) {
            Integer rand = (int) Math.ceil(Math.random() * 550000);

            // Retrieve value. We are not using it anywhere
            Integer value = map.get(String.valueOf(rand));

            // Put value
            map.put(String.valueOf(rand), rand);
        }
    }
}

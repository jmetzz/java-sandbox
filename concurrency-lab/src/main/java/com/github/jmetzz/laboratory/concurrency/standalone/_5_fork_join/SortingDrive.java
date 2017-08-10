package com.github.jmetzz.laboratory.concurrency.standalone._5_fork_join;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SortingDrive {

    public static void main(String[] args) {

        List<String> strings = generateRandomStrings();
        Collections.shuffle(strings);
        SortAction<String> sorter = new SortAction<>(strings.toArray(new String[0]));

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        pool.invoke(sorter);

        for(String s: sorter.getResult())
            System.out.println(s);
    }

    private static List<String> generateRandomStrings() {
        return new ArrayList<>();
    }

}

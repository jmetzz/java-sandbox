package com.github.jmetzz.basics;

import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;

import java.util.ArrayList;
import java.util.List;

public class EnhencedForLoopExample {


    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("One");
        list.add("two");
        list.add(null);
        list.add("three");


        FluentIterable<String> ilist = FluentIterable.from(list).filter(Predicates.<String>notNull());
        for (String s : ilist)
            System.out.println(s.length());

        // and now a java.lang.NullPointerException
        for (String s : list)
            System.out.println(s.length());

    }

}

package com.github.jmetzz.functional.guava.collections;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by exi853 on 12/02/2016.
 */
public class FilterExample {

    public static void main(String[] args) {
        List<String> filterCriteria = Lists.newArrayList("F", "E", "M", "A");

        List<Passenger> passengers = new ArrayList<Passenger>();

        passengers.add( new Passenger("Isaac", "Newton", "F"));
        passengers.add(new Passenger("Ada", "Lovelace", "C"));
        passengers.add(new Passenger("Charles", "Babbage", "C"));
        passengers.add(new Passenger("John", "Nash", "M"));
        passengers.add(new Passenger("Donald", "Knuth", "C"));
        passengers.add(new Passenger("Edsger", "Dijkstra", "C"));
        System.out.println(Collections2.transform(passengers, extracType));

        List<Passenger> filteredList = new ArrayList<Passenger>();


        for(Passenger p: passengers) {
            if(Predicates.in(filterCriteria).apply(extracType.apply(p)))
                filteredList.add(p);
        }
        System.out.println(filteredList);
        System.out.println("==============");
        FluentIterable<Passenger> it = FluentIterable.from(passengers);
        System.out.println(it.transform(extracType).filter(Predicates.in(filterCriteria)).toList());


        String[] array = {};


        List<String> list = Lists.newArrayList(array);
        System.out.println(list);

        System.out.println(String.valueOf(true));

    }

    static Function<Passenger, String> extracType = new Function<Passenger, String>() {
        @Override
        public String apply(Passenger input) {
            return input.getType();
        }
    };

}

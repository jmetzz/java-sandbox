package com.github.jmetzz.functional.guava.optional;

import com.github.jmetzz.functional.guava.pojos.InventoryOrder;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exi853 on 06/04/2016.
 */
public class OptionalExample {


    public static final String DEFAULT = "#Default String#";
    private static final String QUESTION_MARK = "?";

    public static void main(String[] args) {
        System.out.println("=========================");
        System.out.println("First test:");
        System.out.println("=========================");


        Integer value1 =  null;
        Integer value2 =  null;

        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);

        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = null;
        try {
            b = Optional.of(value2);
        }catch (NullPointerException ex){
            System.out.println("Optional.of has thrown NullPointerException because the passed parameter is null");
            System.out.println("Setting a valid object ...");
        }

        value2 =  new Integer(10);
        b = Optional.of(value2);
        System.out.println(sum(a,b));

        // -------------------
        System.out.println("\n\n=========================");
        System.out.println("Second test:");
        System.out.println("=========================");

        List<InventoryOrder> list = new ArrayList<InventoryOrder>();
        list.add(null);

        Card c = new Card("1111111", null);
        System.out.println("Card number: " + Optional.fromNullable(c.getNumber()).or(DEFAULT));
        System.out.println("Card type: " + Optional.fromNullable(c.getType()).or(DEFAULT));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());

        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }


    private static class Card {
        private String number;
        private String type;

        public Card(String number, String type) {
            this.number = number;
            this.type = type;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }



}

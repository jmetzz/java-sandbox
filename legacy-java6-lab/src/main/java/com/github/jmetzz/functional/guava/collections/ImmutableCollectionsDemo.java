package com.github.jmetzz.functional.guava.collections;


import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class ImmutableCollectionsDemo {


    public static final ImmutableSet<SimpleColor> COLOR_NAMES = ImmutableSet.of(
            new SimpleColor("red"),
            new SimpleColor("orange"),
            new SimpleColor("yellow"),
            new SimpleColor("green"),
            new SimpleColor("blue"),
            new SimpleColor("purple")
    );


    public static void main(String[] args) {

        ImmutableSet<SimpleColor> someColors =
                ImmutableSet.<SimpleColor>builder()
                        .addAll(COLOR_NAMES)
                        .add(new SimpleColor("black"))
                        .add(new SimpleColor("cyan"))
                        .add(new SimpleColor("navy"))
                        .build();

        ImmutableSet<SimpleColor> anotherSet = ImmutableSet.copyOf(someColors); //defensive copy

        anotherSet.iterator().next().setName("XXXXXX"); // the set is immutable, but not its contents!
        // Whenever you try to change an immutable collection
        // you will get a java.lang.UnsupportedOperationException
        // if you don't trust me, uncomment the next line :)
        // anotherSet.add(new SimpleColor("test"));

        System.out.println(someColors);
        System.out.println(anotherSet);

    }


    class Foo {
        final ImmutableSet<SimpleColor> colors;

        public Foo(Set<SimpleColor> colors) {
            this.colors = ImmutableSet.copyOf(colors); // defensive copy!
        }
    }

    private static class SimpleColor {
        private String name;

        public SimpleColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("name", name)
                    .toString();
        }
    }

}

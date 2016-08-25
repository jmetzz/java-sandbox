package com.github.jmetzz.functional.guava.predicates;


import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.google.common.base.Predicates.or;

public class PredicatesExample {



    private static final Predicate<Segment> IS_A = new Predicate<Segment>() {
        @Override
        public boolean apply(Segment seg) {
            return seg.inventorySystem == InventorySystem.A;
        }
    };

    private static final Predicate<Segment> IS_B = new Predicate<Segment>() {
        @Override
        public boolean apply(Segment seg) {
            return seg.inventorySystem == InventorySystem.B;
        }
    };


    public static void main(String[] args) {


        List<Segment> segments = Lists.newArrayList();
        segments.add(new Segment(1, InventorySystem.A, "segment 1"));
        segments.add(new Segment(2, InventorySystem.B, "segment 2"));
        segments.add(new Segment(3, InventorySystem.A, "segment 3"));
        segments.add(new Segment(4, InventorySystem.B, "segment 4"));
        segments.add(new Segment(5, InventorySystem.A, "segment 5"));

        boolean matching = FluentIterable.from(segments)
//                .allMatch(or(IS_A, IS_B));
                .allMatch(IS_A);

        System.out.println("All match the predicate  A | B : "+ matching);

        FluentIterable<Segment> filteredList = FluentIterable.from(segments)
                .filter(Predicates.<Segment>isNull());

        System.out.println(filteredList);
        matching = filteredList.allMatch(or(IS_A, IS_B));
        System.out.println("Filtered list - all match the predicate  A | B : " + matching);
    }

    private enum InventorySystem {
        A("A"), B("B");

        private final String desc;

        InventorySystem(String desc){
            this.desc = desc;
        }

        public String toString(){
            return desc;
        }
    }

    private static class Segment {

        final int id;
        final InventorySystem inventorySystem;
        final String description;

        private Segment(int id, InventorySystem inventorySystem, String description) {
            this.id = id;
            this.inventorySystem = inventorySystem;
            this.description = description;
        }


        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", id)
                    .add("inventorySystem", inventorySystem)
                    .add("description", description)
                    .toString();
        }
    }

}

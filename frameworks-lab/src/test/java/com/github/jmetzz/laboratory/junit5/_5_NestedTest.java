package com.github.jmetzz.laboratory.junit5;


import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Nested;
import org.junit.gen5.api.Test;
import static org.junit.gen5.api.Assertions.*;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


/*
 * JUnit supports test case hierarchies via nesting,
 * which enables you to group tests logically under a common parent.
 * This approach facilitates the reuse of initialization methods
 * that are common for all nested methods.
* */
public class _5_NestedTest {

    private Queue<String> items;

    @BeforeEach
    void setup(){
        items  = new LinkedList<>();
    }

    @Test
    void shouldBeEmpty(){
        assertTrue(items.isEmpty());
    }

    @Nested
    class WhenEmpty{
        @Test
        public void removeShouldThrowException(){
            expectThrows(NoSuchElementException.class, items::remove);
        }
    }

    @Nested
    class WhenWithOneElement{

        @Test
        void addingOneElementShouldIncreaseSize(){
            items.add("New item");
            assertEquals(items.size(), 1);
        }
    }

}

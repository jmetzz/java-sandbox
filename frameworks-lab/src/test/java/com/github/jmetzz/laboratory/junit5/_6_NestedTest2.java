package com.github.jmetzz.laboratory.junit5;


import org.junit.gen5.api.*;

import java.util.*;

import static org.junit.gen5.api.Assertions.*;


/*
 * A more complete example (from: junit.org documentation)
 * Observe the logical relationship and meaning of the nested test classes.
 * Does it make sense? :)
* */
public class _6_NestedTest2 {
    Stack<Object> stack;
    boolean isRun = false;

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<Object>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void init() {
            stack = new Stack<Object>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            Assertions.assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            Assertions.expectThrows(EmptyStackException.class, () -> stack.pop());
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            Assertions.expectThrows(EmptyStackException.class, () -> stack.peek());
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void init() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            void isEmpty() {
                Assertions.assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                Assertions.assertEquals(anElement, stack.pop());
                Assertions.assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                Assertions.assertEquals(anElement, stack.peek());
                Assertions.assertFalse(stack.isEmpty());
            }
        }
    }

}

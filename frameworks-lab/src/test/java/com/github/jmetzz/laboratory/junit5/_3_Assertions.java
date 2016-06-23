package com.github.jmetzz.laboratory.junit5;


import org.junit.gen5.api.Test;

import static org.junit.gen5.api.Assertions.*;

/*
 * JUnit5 enables the use of lambda expressions with assertion methods
 * by providing overloaded versions that take an instance of
 * java.util.function.Supplier
 * */
public class _3_Assertions {


    @Test
    void assertionShouldBeTrue() {
        assertEquals(2 == 2, true);
    }

    @Test
    void assertionShouldBeTrueWithLambda() {
        // overloaded assertion method:
        assertEquals(3 == 2, true, () -> "3 not equals to 2!");
    }

    /**
     * When grouping assertions, all assertions are executed together
     * and, in case of failure, all failures are reported together
     */
    @Test
    void groupedAssertions() {

        Person person = new Person("John", "Doe");
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
        assertAll("address",
                () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    /**
     * Exception testing is now handled with lambdas inside the
     * assertion statement.
     */
    @Test
    void expectinArithmeticException() {
        assertThrows(ArithmeticException.class, () -> divideByZero());
    }

    /**
     * It is also possible to assign the exception to a object
     * in order to assert conditions on its values
     */
    @Test
    void exceptionTesting() {
        Throwable iaException = expectThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", iaException.getMessage());

        StringIndexOutOfBoundsException ofbException = expectThrows(StringIndexOutOfBoundsException.class,
                () -> "A string".substring(-1));
        assertEquals(ofbException.getMessage(), "String index out of range: -1");
    }


    private int divideByZero() {
        return 1 / 0;
    }


    private class Person {

        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

}

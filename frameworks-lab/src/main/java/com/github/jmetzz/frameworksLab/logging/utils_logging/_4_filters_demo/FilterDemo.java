package com.github.jmetzz.frameworksLab.logging.utils_logging._4_filters_demo;

import java.util.logging.*;

public class FilterDemo {
    public static final Logger logger = Logger.getLogger(FilterDemo.class.getName());

    public static void main(String args[]) {

        Person person1 = new Person("Paul", 32);
        Person person2 = new Person("Sam", 29);


        logger.setFilter(new AgeFilter());
        // should log only: INFO: TAG1 - Person has age 32
        // Reason:
        // 1.     person1.age > 30
        // 2.     person2.age < 30
        // 3.     "wrong argument" is not Person, so the age can not be checked
        // 4.     there is no Person argument to check the age
        logMessages(person1, person2);

        logger.setFilter(new AnotherFilter());
        // should log only:
        //      INFO: TAG2 - Person has age 29
        //      INFO: TAG2 - Person has age WA
        //      INFO: TAG2 - Person has age NA
        // Reason:
        // 1.     starts with TAG1
        // 2.     does not start with TAG1 | TAG3 | TAG5
        // 3.     does not start with TAG1 | TAG3 | TAG5
        // 4.     does not start with TAG1 | TAG3 | TAG5
        logMessages(person1, person2);

    }

    private static void logMessages(Person person1, Person person2) {
        logger.log(Level.INFO, "TAG1 - Person has age " + person1.getAge(), person1);
        logger.log(Level.INFO, "TAG2 - Person has age " + person2.getAge(), person2);
        logger.log(Level.INFO, "TAG2 - Person has age WA", "wrong argument");
        logger.log(Level.INFO, "TAG2 - Person has age NA");
    }
}



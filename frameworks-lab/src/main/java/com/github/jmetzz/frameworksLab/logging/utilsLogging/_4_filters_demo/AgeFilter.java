package com.github.jmetzz.frameworksLab.logging.utilsLogging._4_filters_demo;


import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AgeFilter implements Filter {
    public AgeFilter() {
    }

    /**
     * This is the overridden method from the Filter interface. It checks the
     * Person object associated with the LogRecord checks the age>30, and
     * returns true
     *
     * @param record the LogRecord object
     * @return boolean true/false
     */
    public boolean isLoggable(LogRecord record) {
        // obtaining the Person object from the record
        Object[] objs = record.getParameters();
        if (objs == null)
            return false;

        Person person;
        try {
            person = (Person) objs[0];
        } catch (ClassCastException ex){
            return false;
        }

        return person != null && person.getAge() > 30;
    }
}

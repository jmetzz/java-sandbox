package com.github.jmetzz.frameworksLab.logging.utilsLogging;/*
 Logging In Java with the JDK 1.4 Logging API and Apache log4j
 by Samudra Gupta    
 Apress Copyright 2003 
 ISBN:1590590996

 */
import java.util.logging.*;

public class FilterDemo {
	private Logger logger = null;
	private AgeFilter filter = null;

	public FilterDemo() {
		// obtaining a logger object
		logger = Logger.getLogger("current.package");
		// creating a AgeFilter object
		filter = new AgeFilter();
		// attaching the filter to the logger
		logger.setFilter(filter);
	}

	/**
	 * This method logs the message
	 */
	public void logMessage(Person person) {
		// logging the message with Person object as parameter
		logger.log(Level.INFO, "Person has age " + person.getAge(), person);
	}

	public static void main(String args[]) {
		FilterDemo demo = new FilterDemo();
		// creating Person objects
		Person person1 = new Person("Paul", 32);
		Person person2 = new Person("Sam", 29);
		// logging with each Person object
		demo.logMessage(person1);
		demo.logMessage(person2);
	}
}

class Person {
	private String name = null;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}

class AgeFilter implements Filter {
	public AgeFilter() {
	}

	/**
	 * This is the overridden method from the Filter interface. It checks the
	 * Person object associated with the LogRecord checks the age>30, and
	 * returns true
	 * 
	 * @param record
	 *            the LogRecord object
	 * @return boolean true/false
	 */
	public boolean isLoggable(LogRecord record) {
		boolean result = false;
		// obtaining the Person object from the record
		Object[] objs = record.getParameters();
		Person person = (Person) objs[0];

		// check if person is not null
		if (person != null) {
			// obtain the age
			int age = person.getAge();
			if (age > 30)
				result = true;
			else
				result = false;
		}
		return result;
	}
}

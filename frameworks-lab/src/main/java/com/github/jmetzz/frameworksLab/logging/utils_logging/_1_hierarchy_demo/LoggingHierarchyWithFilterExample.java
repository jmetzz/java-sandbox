package com.github.jmetzz.frameworksLab.logging.utils_logging._1_hierarchy_demo;

import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Filter;

public class LoggingHierarchyWithFilterExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Logger logger      = Logger.getLogger("");
		Logger logger1     = Logger.getLogger("1");
		Logger logger1_2   = Logger.getLogger("1.2");

		logger1.addHandler  (new ConsoleHandler());
		logger1_2.addHandler(new ConsoleHandler());

		logger1.setFilter(new Filter() {
		    public boolean isLoggable(LogRecord record) {
		        return false;
		    }
		});

		logger     .info("msg:");
		logger1    .info("msg: 1");
		logger1_2  .info("msg: 1.2");
		

	}

}

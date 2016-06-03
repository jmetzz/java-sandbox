package com.github.jmetzz.frameworksLab.logging.utilsLogging._4_filters_demo;

/*
 * A custom filter provides optional, secondary control 
 * over what is logged, beyond the control that is 
 * provided by the level.
 *
 * This code will register the log filter with the
 * root Logger's handlers:
 * ...
 * Logger rootLogger = Logger.getLogger("");
 * rootLogger.setFilter(new AnotherFilter());
 */

/**
 * This class filters out all log messages starting with SECJ022E, SECJ0373E, or SECJ0350E.
 */
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AnotherFilter implements Filter {
	public boolean isLoggable(LogRecord lr) {
		String msg = lr.getMessage();
		return !(msg.startsWith("TAG1")
				|| msg.startsWith("TAG3")
				|| msg.startsWith("TAG5")) ;
	}
}

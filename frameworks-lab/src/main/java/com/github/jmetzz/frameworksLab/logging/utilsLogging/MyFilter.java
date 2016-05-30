package com.github.jmetzz.frameworksLab.logging.utilsLogging;/*
 * A custom filter provides optional, secondary control 
 * over what is logged, beyond the control that is 
 * provided by the level.
 *
 * This code will register the above log filter with the
 * root Logger's handlers (including the WAS system logs):
 * ...
 * Logger rootLogger = Logger.getLogger("");
 * rootLogger.setFilter(new MyFilter());
 */

/**
 * This class filters out all log messages starting with SECJ022E, SECJ0373E, or SECJ0350E.
 */
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class MyFilter implements Filter {
	public boolean isLoggable(LogRecord lr) {
		String msg = lr.getMessage();
		if (msg.startsWith("SECJ0222E") || msg.startsWith("SECJ0373E")
				|| msg.startsWith("SECJ0350E")) {
			return false;
		}
		return true;
	}
}

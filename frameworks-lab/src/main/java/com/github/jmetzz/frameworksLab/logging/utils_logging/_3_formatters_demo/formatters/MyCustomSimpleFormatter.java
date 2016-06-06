package com.github.jmetzz.frameworksLab.logging.utils_logging._3_formatters_demo.formatters;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * MyCustomSimpleFormatter formats the LogRecord as follows:
 * date   level   localized message with parameters 
 */
public class MyCustomSimpleFormatter extends Formatter {

	public MyCustomSimpleFormatter() {
		super();
	}

	public String format(LogRecord record) {
		
		// Create a StringBuffer to contain the formatted record
		// start with the date.
		StringBuffer sb = new StringBuffer();
		
		// Get the date from the LogRecord and add it to the buffer
		Date date = new Date(record.getMillis());
		sb.append(date.toString());
		sb.append(" ");
		
		// Get the level name and add it to the buffer
		sb.append(record.getLevel().getName());
		sb.append(" ");
		 
		// Get the formatted message (includes localization 
		// and substitution of paramters) and add it to the buffer
		sb.append(formatMessage(record));
		sb.append("\n");

		return sb.toString();
	}
}
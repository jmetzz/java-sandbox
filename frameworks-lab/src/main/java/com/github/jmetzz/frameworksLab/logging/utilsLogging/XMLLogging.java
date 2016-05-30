package com.github.jmetzz.frameworksLab.logging.utilsLogging;/*
 Logging In Java with the JDK 1.4 Logging API and Apache log4j
 by Samudra Gupta    
 Apress Copyright 2003 
 ISBN:1590590996

 */
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.XMLFormatter;

public class XMLLogging {
	private ConsoleHandler ch;
	private XMLFormatter formatter;
	private FileHandler handler = null;

	/**
	 * constructor
	 */
	public XMLLogging() {
		ch = new ConsoleHandler();
		formatter = new XMLFormatter();
	}

	/**
	 * This method demonstrates the logging using XMLFormatter and should there
	 * be an exception, the exception is logged into the console with
	 * ConsoleHandler and formatted withXMLFormatter.
	 */

	public void logMessage() {
		// creating a LogRecord object with level and message
		LogRecord record = new LogRecord(Level.INFO, "XML message..");

		try {
			// creating a StreamHandler object to file output the xml message
			handler = new FileHandler("newxml.xml");
			handler.setFormatter(formatter);

			// publishing the log message to the file and flushing the buffer
			handler.publish(record);
			handler.flush();
		} catch (Exception e) {
			// creating a log record object with the WARNING level
			// and exception message
			LogRecord rec = new LogRecord(Level.WARNING, e.toString());

			// setting the formatter for the consolehandler as
			// XMLFormatter and publishing the message
			ch.setFormatter(formatter);
			ch.publish(rec);
		}
	}

	public static void main(String args[]) {
		XMLLogging logging = new XMLLogging();
		logging.logMessage();
	}
}
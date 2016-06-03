package com.github.jmetzz.frameworksLab.logging.utilsLogging._2_handlers_demo.xml;

import java.util.logging.*;

public class XMLLogging {
	private ConsoleHandler consoleHandler;
	private Formatter formatter;
	private FileHandler handler = null;

	public XMLLogging() {
		consoleHandler = new ConsoleHandler();
		formatter = new XMLFormatter();
	}

	/**
	 * This method demonstrates the logging using XMLFormatter and should there
	 * be an exception, the exception is logged into the console with
	 * ConsoleHandler and formatted with XMLFormatter.
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
			consoleHandler.setFormatter(formatter);
			consoleHandler.publish(rec);
		}
	}

	public static void main(String args[]) {
		XMLLogging logging = new XMLLogging();
		logging.logMessage();
	}
}
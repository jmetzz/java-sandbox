package com.github.jmetzz.laboratory.logging.utils_logging._3_formatters_demo;

import java.io.IOException;
import java.util.logging.*;

public class XMLFormatterDemo {

	public static final String BASE_PROJECT_URL = "./frameworks-lab/";
	public static final String PACKAGE_RESOURCES = BASE_PROJECT_URL + "src/test/resources/logging-resources/";

	private ConsoleHandler consoleHandler;
	private Formatter formatter;
	private FileHandler handler = null;

	public XMLFormatterDemo() {
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
		LogRecord record = new LogRecord(Level.INFO, "XML message...");

		try {
			// creating a StreamHandler object to file output the xml message
			handler = new FileHandler(PACKAGE_RESOURCES + "newxml.xml");
			handler.setFormatter(formatter);

			// publishing the log message to the file and flushing the buffer
			handler.publish(record);
			handler.flush();
		} catch (IOException  | SecurityException e) {
			LogRecord rec = new LogRecord(Level.WARNING, e.toString());
			consoleHandler.setFormatter(formatter);
			consoleHandler.publish(rec);
		} finally {
            handler.close();
        }
    }

	public static void main(String args[]) {
		XMLFormatterDemo logging = new XMLFormatterDemo();
		logging.logMessage();
	}
}
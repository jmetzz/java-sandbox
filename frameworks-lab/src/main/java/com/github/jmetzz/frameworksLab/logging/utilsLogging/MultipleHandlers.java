package com.github.jmetzz.frameworksLab.logging.utilsLogging;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MultipleHandlers {

	private static Logger logger = Logger.getLogger("MultipleHandlers");

	public static void main(String[] args) throws Exception {
		FileHandler logFile = new FileHandler("MultipleHandlers.xml");
		logger.addHandler(logFile);
		logger.addHandler(new ConsoleHandler());
		logger.warning("Output to multiple handlers");

	}
} 

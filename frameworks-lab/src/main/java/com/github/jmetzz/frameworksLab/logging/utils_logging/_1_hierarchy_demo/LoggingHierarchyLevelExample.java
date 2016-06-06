package com.github.jmetzz.frameworksLab.logging.utils_logging._1_hierarchy_demo;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingHierarchyLevelExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("");
		Logger logger1 = Logger.getLogger("1");
		Logger logger1_2 = Logger.getLogger("1.2");


		/*
		 * logger config: Level, ResourceBundleName, Handlers
		 */
		System.out.println(getConfig(logger));
		System.out.println("-------");
		System.out.println(getConfig(logger1));
		System.out.println("-------");
		System.out.println(getConfig(logger1_2));
		System.out.println("-------");
		
		logger.info("msg:");
		logger1.info("msg: 1");
		logger1_2.info("msg: 1.2");
		
		System.out.println("#############");
		
		// setting handlers. The level does not need
		// since it has a default handler
		logger1.setLevel(Level.SEVERE);

		logger.info("msg:");
		logger1.info("msg: 1");
		logger1_2.info("msg: 1.2");

		System.out.println("#############");

		logger1_2.setLevel(Level.FINE);
		
		logger.info("msg:");
		logger1.info("msg: 1");
		logger1_2.info("msg: 1.2");
		
		System.out.println("#############");
	}
	
	public static String getConfig(Logger l){
		StringBuilder str = new StringBuilder();
		str.append("Logger name: ").append(l.getName());
		str.append("\n\tLevel :" + l.getLevel());
		str.append("\n\tResourceBundle :" + l.getResourceBundleName());
		str.append("\n\tHandlers :" + Arrays.asList(l.getHandlers()));
		return str.toString();
	}


}

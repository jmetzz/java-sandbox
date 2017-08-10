package com.github.jmetzz.laboratory.logging.utils_logging._1_hierarchy_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoggingHierarchyExample {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String loggerNames = "com.github.jmetzz.logging.app";
		Logger logger = Logger.getLogger(loggerNames);
		System.out.println("Logger hierarchy without" +
				" initializing every level:");
		System.out.println("\tLogger: " + logger.getName() + "$");
		System.out.println("\tLogger parent: " + logger.getParent().getName()+ "$");
		System.out.println("\tLevel: " + logger.getLevel()+ "$");
		System.out.println(" --------- ");

		List<String> namesList = new ArrayList<String>();
		while (loggerNames.contains(".")) {
			namesList.add(loggerNames);
			loggerNames = loggerNames.substring(0, loggerNames.lastIndexOf("."));
		}
		namesList.add(loggerNames);
		System.out.println(namesList);

		List<Logger> loggersList = new ArrayList<Logger>();
		for (String name : namesList) {
			loggersList.add(Logger.getLogger(name));
		}

		System.out.println("\nA logger hierarchy with all levels created ");
		for (Logger l : loggersList) {
			System.out.println("\t Logger: " + l.getName() + "$ Parent: "
					+ l.getParent().getName() + "$ Level: " + l.getLevel() + "$");
		}

		
	}

}

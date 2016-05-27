package com.github.jmetzz.apachecommons.cli.propertiesFiles;

import java.util.Map.Entry;
import java.util.Properties;

public class AppTest {

	public static void main(String[] args) {
		String path = Configuration.getProperty("path");
		String file = Configuration.getProperty("test");
		Boolean a = Configuration.getBooleanProperty("a");
		Boolean b = Configuration.getBooleanProperty("b");

		System.out.println("Path = " + path);
		System.out.println("File = " + file);
		System.out.println("a = " + a);
		System.out.println("b = " + b);

		printHelp();
	}

	private static void printHelp() {
		StringBuffer str = new StringBuffer();
		Properties prop = Configuration.getOptions();
		str.append("+ + + + + Help + + + + + \n");
		for (Entry e : prop.entrySet()) {
			str.append("\t");
			str.append(e);
			str.append("\n");
		}
		str.append("+ + + + + + + + + + + + \n");
		System.out.println(str);
		
	}
}

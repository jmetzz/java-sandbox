package com.github.jmetzz.apachecommons.cli.javacollections;

import java.util.Set;
import java.util.TreeSet;

public class AppTest {

	public Set<String> mandatoryOptions;
	public Set<String> optionalOptions;
	public Set<String> mandatoryFlags;
	public Set<String> optionalFlags;

	public AppTest() {
		mandatoryOptions = new TreeSet<String>();
		optionalOptions = new TreeSet<String>();
		mandatoryFlags = new TreeSet<String>();
		optionalFlags = new TreeSet<String>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AppTest app = new AppTest();

		app.mandatoryOptions.add("--path");
		app.optionalOptions.add("--test");
		app.optionalFlags.add("-a");
		app.optionalFlags.add("-b");

		ParseOptions parser = new ParseOptions(
				app.mandatoryOptions,
				app.mandatoryFlags, app.optionalOptions, 
				app.optionalFlags);

		try {
			parser.parse(args);
			System.out.println(parser.getOptions());
		} catch (Exception e) {
			System.out.println("Wrong options informed.  See usage information bellow:");
			usage();
		}

	}

	public static void usage() {
		StringBuffer usage = new StringBuffer();

		usage.append("usage: java [JVM options] -cp <class path> ");
		usage.append(AppTest.class.getSimpleName());
		usage.append(" [--path <directory>] [--test <test file>] [-a] [-b]");
		usage.append("\n\n");
		System.out.print(usage.toString());

	}

}

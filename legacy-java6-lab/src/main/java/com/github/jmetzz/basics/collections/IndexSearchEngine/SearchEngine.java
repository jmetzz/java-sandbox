package com.github.jmetzz.basics.collections.IndexSearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SearchEngine {

	Map<String, String> options;
	SimpleInvertedIndex invIndex;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			SearchEngine se = new SearchEngine();
			se.run(args);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getCause().getMessage());
			System.exit(-1);
		}

	}

	public void run(String[] args) {
		try {
			setOptions(args);
		} catch (IllegalArgumentException e) {
			System.out.println("Wrong arguments or paramns");
			System.out.println("[TECH INFO]");

			printUsage();
			System.out
					.println("======================================================================");
			System.out.println("CAUSE:");
			System.out.println(e.getCause().getMessage());
			System.exit(1);
		}
		
		

		invIndex = new SimpleInvertedIndex(getPath());
		try {
			invIndex.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		String option;
		do {
			option = "";
			System.out.print("Type your query: ");
			String query = scan.next();
			//Set<String> result = invIndex.getWords(query);
			Set<String> result = invIndex.executeQuery(query);
			System.out.println("\tResult: " + result);
			
			while( !(option.equals("new") || option.equals("bye"))  ){
				System.out.print("new or bye? ");
				option = scan.next();
				
				if (!(option.equals("new") || option.equals("bye")))
					System.out.println("\tWrong option. Type new or bye");
			}
		} while (!option.equals("bye"));
		System.out.println("Bye bye!");

	}

	private void printUsage() {

		StringBuffer usage = new StringBuffer();
		usage.append("usage: java [options] ");
		usage.append(SearchEngine.class);
		usage.append(" --path <directory>");
		usage.append("\n\n");

		System.out.print(usage.toString());
		System.exit(1);
	}

	protected void setOptions(String[] args) throws IllegalArgumentException {
		if (args.length == 0) {
			throw new IllegalArgumentException("No options given.");
		}

		options = new LinkedHashMap<String, String>();
		boolean value = false;

		// parses the command line arguments
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("--")) {
				if ("--path".equals(args[i])) {
					options.put(args[i], null);
					value = true;
				} else {
					throw new IllegalArgumentException("Invalid option: "
							+ args[i]);
				}
			} else if (value) {
				options.put(args[i - 1], args[i]);
				value = false;
			} else {
				options.put(args[i], null);
			}
		}

		if (options.containsKey("--path")) {
			String directory = options.get("--path");
			if ((directory == null) || !(new File(directory)).isDirectory()) {
				throw new IllegalArgumentException("Wrong argument value: "
						+ "--path must be a valid directory");
			}
		}

	}

	public String getPath() {
		return options.get("--path");
	}

}

package com.github.jmetzz.basics.collections.demoApps.indexSearchEngine;

import com.google.common.base.Optional;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SearchEngineDemo {
	public static final String DEFAULT = "not indexed";
	Map<String, String> options;
	Index invIndex;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// use --path ./legacy-java6-lab/src/main/resources/Texts/
		try {
			SearchEngineDemo se = new SearchEngineDemo();
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
			System.out.println("Wrong arguments or paramms");
			printUsage();

			System.out.println("CAUSE:");
			System.out.println(e.getLocalizedMessage());
			System.exit(1);
		}
		
//		invIndex = new SimpleInvertedIndex(getPath());
		invIndex = new ImprovedInvertedIndex(getPath());
		try {
			invIndex.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
        while (true) {
			System.out.print("New query (bye-bye to leave): ");
			String query = scan.next();

            if (query.equals("bye-bye"))  break;

			Set<String> result = invIndex.executeQuery(query);
			System.out.println("\tFound in: " + Optional.fromNullable(result).or(new HashSet<String>()));
		}
		System.out.println("Bye bye!");

	}

	private void printUsage() {

		StringBuffer usage = new StringBuffer();
		usage.append("usage: java [options] ")
				.append(SearchEngineDemo.class)
				.append(" --path <directory>")
				.append("\n")
				.append("----------------------------")
				.append("\n\n");
		System.out.print(usage.toString());
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

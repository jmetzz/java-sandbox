package com.github.jmetzz.apachecommons.cli;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.*;

public class DateApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Options options = new Options();
		// option, has arguments?, description
		options.addOption("t", false, "display current time");
		options.addOption("c", true, "country code");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;

		Date dNow = new Date();
		SimpleDateFormat ft;

		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("t")) {
				System.out.println(dNow.toString());
			} else {
				ft = new SimpleDateFormat("dd/MM/yyyy");
				System.out.println("Current Date: " + ft.format(dNow));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}

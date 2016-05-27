package com.github.jmetzz.apachecommons.cli;

import org.apache.commons.cli.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateApp2 {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("t", false, "display current time");
        options.addOption("c", true, "country code");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        Date dNow = new Date();
        SimpleDateFormat ft;

        try {
            cmd = parser.parse(options, args);
            String countryCode = cmd.getOptionValue("c");

            if (cmd.hasOption("t")) {
                if (countryCode == null) {
                    System.out.println(dNow.toString());
                } else {
                    // print date and time for the given country
                }
            } else {
                if (countryCode == null) {
                    ft = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Current Date: " + ft.format(dNow));
                } else {
                    // print date for the given country
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

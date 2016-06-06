package com.github.jmetzz.frameworksLab.logging.utils_logging._3_formatters_demo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomHtmlFormatterDemo {
    // Always use the classname, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(CustomHtmlFormatterDemo.class.getName());

    public static final String BASE_PROJECT_URL = "./frameworks-lab/";
    public static final String PACKAGE_RESOURCES = BASE_PROJECT_URL + "src/test/resources/logging-resources/";


    public void writeLog() {
        // Set the LogLevel to Severe, only severe Messages will be written
        LOGGER.setLevel(Level.SEVERE);
        LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Really not important");

        // Set the LogLevel to Info, severe, warning and info will be written
        // Finest is still not written
        LOGGER.setLevel(Level.INFO);
        LOGGER.severe("Info Log");
        LOGGER.warning("Info Log");
        LOGGER.info("Info Log");
        LOGGER.finest("Really not important");
    }

    public static void main(String[] args) {
        CustomHtmlFormatterDemo logger = new CustomHtmlFormatterDemo();
        try {
            MyLogger.setup(PACKAGE_RESOURCES + "LOG-CustomHtmlFormatterDemo");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        logger.writeLog();
    }
} 

package com.github.jmetzz.log4jDdemo;


import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.util.Enumeration;

public class Log4jDemoApp {


    public static final String BASE_PROJECT_URL = "./frameworks-lab/log4j-lab/";
    public static final String TEST_RESOURCES = BASE_PROJECT_URL + "src/test/resources/";


    // don't throw exception in your main method, handle it!!
    public static void main(String[] args) throws IOException {
        System.out.println("--------------------- basicConfiguration ---------------------");
        basicConfiguration();
        System.out.println("\n\n");

        System.out.println("--------------------- programmaticConfiguration ---------------------");
        programmaticConfiguration(TEST_RESOURCES + "applog3.txt");
        System.out.println("\n\n");


        System.out.println("--------------------- defaultHTMLAppenderConfiguration ---------------------");
        defaultHTMLAppenderConfiguration(TEST_RESOURCES + "Log4JHTMLLayout.html");
        System.out.println("\n\n");

        System.out.println("--------------------- propertiesFileConfiguration(example1.properties) ---------------------");
        propertiesFileConfiguration(TEST_RESOURCES + "ex1.properties");
        System.out.println("\n\n");

        System.out.println("--------------------- propertiesFileConfiguration(example2.properties) ---------------------");
        propertiesFileConfiguration(TEST_RESOURCES + "ex2.properties");
        System.out.println("\n\n");

        System.out.println("--------------------- xmlFileConfiguration ---------------------");
        xmlFileConfiguration(TEST_RESOURCES + "log4j-xml-configuration.xml");
        System.out.println("\n\n");

        System.out.println("--------------------- testLoggerLevelInheritance ---------------------");
        testLoggerLevelInheritance();
        System.out.println("\n\n");

        System.out.println("--------------------- testLoggerLevelInheritance(filename) ---------------------");
        testLoggerLevelInheritance(TEST_RESOURCES + "exHierarchy.xml");


    }


    public static void basicConfiguration() {
        Logger logger = Logger.getLogger(Log4jDemoApp.class.getName());
        BasicConfigurator.configure();
        logMessages(logger);
    }

    public static void programmaticConfiguration(String outputFileName) {
        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile(outputFileName);
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.WARN);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

        // creates a custom logger and log messages
        Logger logger = Logger.getLogger(Log4jDemoApp.class);
        logger.setLevel(Level.DEBUG);

        logger.debug("this is a debug log message");
        logger.info("this is a information log message");
        logger.warn("this is a warning log message");
        logger.trace("trace test");

    }

    public static void defaultHTMLAppenderConfiguration(String filename) throws IOException {
        Logger logger = Logger.getLogger(Log4jDemoApp.class.getName());
        logger.setLevel(Level.DEBUG);
        Appender appender = new FileAppender(new HTMLLayout(), filename);
        logger.addAppender(appender);
        logMessages(logger);

    }

    public static void propertiesFileConfiguration(String filename) {
        /*
		 * If the file log4j.properties in found (path)
		 * it is not necessary to call configure method.
		 * For this example, log4j.properties contains
		 * the same contend that is in filename (e.g. example.properties).
		 * To test this behaviour comment the configure line
		 */
        Logger logger = Logger.getLogger(Log4jDemoApp.class);
        PropertyConfigurator.configure(filename);
        logMessages(logger);
    }

    public static void xmlFileConfiguration(String filename) {
        Logger logger = Logger.getLogger(Log4jDemoApp.class);
        DOMConfigurator.configure(filename);
        logMessages(logger);
    }

    public static void testLoggerLevelInheritance() {

        Logger comlogger = Logger.getLogger("com");
        comlogger.setLevel(Level.DEBUG);
        comlogger.error("Located nearest gas station.");

        // get a logger instance named "com.foo"
        Logger logger = Logger.getLogger("com.foo");

        // Now set its level. Normally you do not need to set the
        // level of a logger programmatically. This is usually done
        // in configuration files.
        logger.setLevel(Level.INFO);

        // This request is enabled, because WARN >= INFO.
        logger.warn("Low fuel level.");

        // This request is disabled, because DEBUG < INFO.
        logger.debug("Starting search for nearest gas station.");

        // The logger instance barlogger, named "com.foo.Bar",
        // will inherit its level from the logger named
        // "com.foo" Thus, the following request is enabled
        // because INFO >= INFO.
        Logger barlogger = Logger.getLogger("com.foo.Bar");
        barlogger.info("Located nearest gas station.");

        // This request is disabled, because DEBUG < INFO.
        barlogger.debug("Exiting gas station search");

    }

    public static void testLoggerLevelInheritance(String filename) {
        Logger logger = Logger.getLogger(Log4jDemoApp.class);
        DOMConfigurator.configure(filename);

        System.out.println("Configured loggers: ");

        Enumeration e = logger.getLoggerRepository().getCurrentLoggers();
        System.out.println("Root logger: " + logger.getRootLogger().getName() + ", level: " + logger.getRootLogger().getLevel());
        while (e.hasMoreElements()) {
            Logger l = (Logger) e.nextElement();
            System.out.println(l.getName() + ", level: " + l.getLevel());
        }

        System.out.println("-----------");
        System.out.println("Log messages ");

        logMessages(logger);
    }

    private static void logMessages(Logger logger) {
        logger.fatal("Hello this is an fatal message");
        logger.error("Hello this is an error message");
        logger.warn("Hello this is an warning message");
        logger.info("Hello this is an info message");
        logger.debug("Hello this is an debug message");
        logger.trace("Hello this is an trace message");
    }


}

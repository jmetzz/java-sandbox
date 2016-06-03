package com.github.jmetzz.frameworksLab.logging.utilsLogging._3_formatters_demo.custom_formatter;

import com.github.jmetzz.frameworksLab.logging.utilsLogging._3_formatters_demo.formatters.MyCustomHtmlFormatter;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static public void setup(String fileName) throws IOException {
        // Create Logger
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler(fileName + ".txt");
        fileHTML = new FileHandler(fileName + ".html");

        // Create txt Formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // Create HTML Formatter
        formatterHTML = new MyCustomHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
} 

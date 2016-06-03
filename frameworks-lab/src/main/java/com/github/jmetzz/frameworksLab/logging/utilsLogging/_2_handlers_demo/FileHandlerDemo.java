package com.github.jmetzz.frameworksLab.logging.utilsLogging._2_handlers_demo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class FileHandlerDemo {
    private FileHandler handler = null;

    private static Logger logger = Logger.getLogger("sam.logging");

    public FileHandlerDemo(String pattern) {
        try {
            handler = new FileHandler(pattern, 1000, 2);
            logger.addHandler(handler);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void logMessage() {
        LogRecord record = new LogRecord(Level.INFO, "Logged in a file..22.");
        logger.log(record);
        handler.flush();
        handler.close();
    }

    public static void main(String[] args) {
        FileHandlerDemo demo = new FileHandlerDemo("%h/log%g.out");
        demo.logMessage();
    }
}

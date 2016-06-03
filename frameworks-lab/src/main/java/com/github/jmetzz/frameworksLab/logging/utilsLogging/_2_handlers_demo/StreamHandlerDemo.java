package com.github.jmetzz.frameworksLab.logging.utilsLogging._2_handlers_demo;

import java.io.OutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class StreamHandlerDemo {
    private StreamHandler handler = null;

    private OutputStream outStream = null;

    private static Logger logger = Logger.getLogger("sam.logging");

    public StreamHandlerDemo() {
        outStream = System.out;

        handler = new StreamHandler(outStream, new SimpleFormatter());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
    }

    public void logMessage() {
        logger.info("StreamHandler is working!");
    }

    public static void main(String[] args) {
        StreamHandlerDemo demo = new StreamHandlerDemo();
        demo.logMessage();
    }
}

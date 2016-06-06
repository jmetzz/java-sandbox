package com.github.jmetzz.frameworksLab.logging.utils_logging._2_handlers_demo;

import java.io.IOException;
import java.util.logging.*;

public class MemoryHandlerDemo {
    public static final String BASE_PROJECT_URL = "./frameworks-lab/";
    public static final String PACKAGE_RESOURCES = BASE_PROJECT_URL + "src/test/resources/logging-resources/";

    private ConsoleHandler consoleHandler = null;

    private FileHandler fileHandler = null;

    /* Handler that buffers requests in a circular buffer in memory.
    * Normally this Handler simply stores incoming LogRecords into its
    * memory buffer and discards earlier records. This buffering is
    * very cheap and avoids formatting costs. On certain trigger conditions,
    * the MemoryHandler will push out its current buffer contents to a
    * target Handler, which will typically publish them to the outside world.
    * */
    private MemoryHandler mHandler = null;

    private static Logger logger = Logger.getLogger(MemoryHandlerDemo.class.getName());

    public MemoryHandlerDemo(int size, Level pushLevel) throws IOException {
        /* since IO is expensive, let's use a memory handler to keep some messages
        in memory before flushing */
        fileHandler = new FileHandler(PACKAGE_RESOURCES + "MultipleHandlersDemo.xml");
        mHandler = new MemoryHandler(fileHandler, size, pushLevel);
        logger.addHandler(mHandler);

        consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);

        // Specify whether or not this logger should send its output to its parent Logger.
        logger.setUseParentHandlers(false);
    }

    public void logMessage() {
        logger.log(new LogRecord(Level.SEVERE, "This is SEVERE level message"));
        logger.log(new LogRecord(Level.WARNING, "This is WARNING level message"));
    }

    public static void main(String args[]) throws IOException {

        MemoryHandlerDemo demo = new MemoryHandlerDemo(2, Level.SEVERE);
        demo.logMessage();

        MemoryHandlerDemo demo2 = new MemoryHandlerDemo(2, Level.WARNING);
        demo2.logMessage();

    }
}
package com.github.jmetzz.frameworksLab.logging.utilsLogging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;

public class MemoryHandlerDemo {
  private ConsoleHandler handler = null;

  private MemoryHandler mHandler = null;

  private static Logger logger = Logger.getLogger("sam.logging");

  public MemoryHandlerDemo(int size, Level pushLevel) {
    handler = new ConsoleHandler();
    mHandler = new MemoryHandler(handler, size, pushLevel);
    logger.addHandler(mHandler);
    logger.setUseParentHandlers(false);
  }

  public void logMessage() {
    LogRecord record1 = new LogRecord(Level.SEVERE, "This is SEVERE level message");
    LogRecord record2 = new LogRecord(Level.WARNING, "This is WARNING level message");

    logger.log(record1);
    logger.log(record2);
  }

  public static void main(String args[]) {
    MemoryHandlerDemo demo = new MemoryHandlerDemo(2, Level.SEVERE);
    demo.logMessage();
  }
}
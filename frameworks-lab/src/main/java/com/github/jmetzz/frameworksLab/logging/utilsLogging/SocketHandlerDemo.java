package com.github.jmetzz.frameworksLab.logging.utilsLogging;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

public class SocketHandlerDemo {
  private SocketHandler handler = null;

  private static Logger logger = Logger.getLogger("sam.logging");

  public SocketHandlerDemo(String host, int port) {
    try {
      handler = new SocketHandler(host, port);
      logger.addHandler(handler);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public void logMessage() {
    logger.warning("SocketHandler is working...");
  }

  public static void main(String args[]) {
    SocketHandlerDemo demo = new SocketHandlerDemo("localhost", 2020);
    demo.logMessage();
  }
}
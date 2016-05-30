package com.github.jmetzz.frameworksLab.logging.utilsLogging;

import java.util.logging.Logger;

public class BasicLogging1 {

  public static void main(String args[]) {
    Logger logger = Logger.getLogger("current.package");
    logger.info("Simple formatted message..");
  }
}
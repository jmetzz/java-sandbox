package com.github.jmetzz.frameworksLab.logging.utilsLogging._2_handlers_demo.xml;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlternateXML {

    public static void main(String args[]) {
        try {
            FileHandler handler = new FileHandler("alterxml.xml");
            Logger logger = Logger.getLogger("java2s.logging");
            logger.addHandler(handler);
            logger.log(Level.INFO, "alternative xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
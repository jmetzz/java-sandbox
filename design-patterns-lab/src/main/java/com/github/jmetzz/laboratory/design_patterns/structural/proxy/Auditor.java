package com.github.jmetzz.laboratory.design_patterns.structural.proxy;

import com.sun.istack.internal.logging.Logger;

/**
 * Created by Jean Metz.
 */
public class Auditor {
    Logger logger = Logger.getLogger(Auditor.class);

    public void audit(String className, String msg) {
        logger.info("Class: " + className + " ; Message: " + msg);
    }
}

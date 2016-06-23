package com.github.jmetzz.laboratory.logging.utils_logging._2_handlers_demo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class FileHandlerDemo {

    /*
     * From java docs:
     * The FileHandler can either write to a specified file, or it can write to a rotating set of files.
     * By default the XMLFormatter class is used for formatting.
     * Configuration: By default each FileHandler is initialized using the following LogManager configuration properties.
     * If properties are not defined (or have invalid values) then the specified default values are used.
     * java.util.logging.FileHandler.level specifies the default level for the Handler (defaults to Level.ALL).
     * java.util.logging.FileHandler.filter specifies the name of a Filter class to use (defaults to no Filter).
     * java.util.logging.FileHandler.formatter specifies the name of a Formatter class to use (defaults to java.util.logging.XMLFormatter)
     * java.util.logging.FileHandler.encoding the name of the character set encoding to use (defaults to the default platform encoding).
     * java.util.logging.FileHandler.limit specifies an approximate maximum amount to write (in bytes) to any one file. If this is zero, then there is no limit. (Defaults to no limit).
     * java.util.logging.FileHandler.count specifies how many output files to cycle through (defaults to 1).
     * java.util.logging.FileHandler.pattern specifies a pattern for generating the output file name. See below for details. (Defaults to "%h/java%u.log").
     * java.util.logging.FileHandler.append specifies whether the FileHandler should append onto any existing files (defaults to false).
     *
     */
    private FileHandler handler = null;

    private static Logger logger = Logger.getLogger(FileHandlerDemo.class.getName());

    public FileHandlerDemo(String pattern) {
        try {
            handler = new FileHandler(pattern, 1000, 2);
            logger.addHandler(handler);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void logMessage() {
        LogRecord record = new LogRecord(Level.INFO, "Logged in a file....");
        logger.log(record);
        handler.flush();
        handler.close();
    }

    public static void main(String[] args) {
        /*
        Remember that java.util.logging.FileHandler.pattern
        specifies a pattern for generating the output file name. (Defaults to "%h/java%u.log").

        A pattern consists of a string that includes the following special components
        that will be replaced at runtime:

        "/" the local pathname separator
        "%t" the system temporary directory
        "%h" the value of the "user.home" system property
        "%g" the generation number to distinguish rotated logs
        "%u" a unique number to resolve conflicts
        "%%" translates to a single percent sign "%"
        If no "%g" field has been specified and the file count is greater than one,
        then the generation number will be added to the end of the generated filename,
        after a dot.
        */

        /*
        * This example:
        * <user.home>/log<generation number>.out
         */
        FileHandlerDemo demo = new FileHandlerDemo("%h/log%g.out");

        demo.logMessage();
        demo.logMessage();
    }
}

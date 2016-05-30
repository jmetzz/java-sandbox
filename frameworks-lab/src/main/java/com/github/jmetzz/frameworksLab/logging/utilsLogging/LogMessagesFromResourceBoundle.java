package com.github.jmetzz.frameworksLab.logging.utilsLogging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Resource bundle sample
 * 
 * For this sample, four localizable messages are provided.
 * The properties file is created and the key-value pairs 
 * are inserted. All the normal properties file conventions
 * and rules apply to this file. In addition, the creator 
 * must be aware of other restrictions that are imposed on 
 * the values by the Java MessageFormat class. For example, 
 * apostrophes must be escaped or they cause a problem. 
 * Avoid the use of non-portable characters. 
 * Assume that the base directory for the application that
 * uses this resource bundle is baseDir and that this 
 * directory is in the class path. Assume that the properties 
 * file is stored in the subdirectory baseDir that is not in 
 * the class path (for example, baseDir/subDir1/subDir2/resources). 
 * To allow the messages file to resolve, the 
 * subDir1.subDir2.resources.DefaultMessage name is used to 
 * identify the property resource bundle and is passed to the 
 * message logger
 * For this sample, the properties file is named 
 * DefaultMessages.properties
 * 
 */
public class LogMessagesFromResourceBoundle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Logger logger = Logger.getLogger("current.package");
	    logger.setLevel(Level.INFO);
	    logger.addHandler(new ConsoleHandler());
	    
	    logger.logrb(Level.SEVERE, LogMessagesFromResourceBoundle.class.getName(), 
	    		"main", "logger.DefaultMessages", "MSG_KEY_00");
	    logger.logrb(Level.SEVERE, LogMessagesFromResourceBoundle.class.getName(), 
	    		"main", "logger.DefaultMessages", "MSG_KEY_01", new Object[] {"Given Parameter"});

	    logger.logrb(Level.SEVERE, LogMessagesFromResourceBoundle.class.getName(), 
	    		"main", "logger.DefaultMessages", "MSG_KEY_02", new Object[] {"Given Parameter 1", "Given Parameter 2"});

	    logger.logrb(Level.SEVERE, LogMessagesFromResourceBoundle.class.getName(), 
	    		"main", "logger.DefaultMessages", "MSG_KEY_03", new Object[] {"Given Parameter 1", "Given Parameter 2", "Given Parameter 3"});

	    
	}

}

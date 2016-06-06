package com.github.jmetzz.frameworksLab.logging.utils_logging._5_resource_bundle_messages;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
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
public class Log_i18n_MessagesFromResourceBoundle {

    public static final String BASE_PROJECT_URL = "./frameworks-lab/";
    public static final String PACKAGE_RESOURCES = BASE_PROJECT_URL + "src/test/resources/logging-resources/";

    public static void main(String[] args) throws IOException {

        Logger logger = Logger.getLogger(Log_i18n_MessagesFromResourceBoundle.class.getName());
        logger.setLevel(Level.INFO);
        logger.addHandler(new ConsoleHandler());

		/*
        Make sure you have a property file with the bellow name
		in the main resources folder of your project
		*/
        String bundle = "LoggerDefaultMessages";

        // ResourceBundle.getBundle returns a PropertyResourceBundle object containing the key-value pairs from the properties file
        ResourceBundle rb = ResourceBundle.getBundle(bundle, new Locale("pt", "BR"));

        logMessage(logger, rb, "MSG_KEY_00", new Object[]{});
        logMessage(logger, rb, "MSG_KEY_01", new Object[]{"Given Parameter"});
        logMessage(logger, rb, "MSG_KEY_02", new Object[]{"Given Parameter 1", "Given Parameter 2"});
        logMessage(logger, rb, "MSG_KEY_03", new Object[]{"Given Parameter 1", "Given Parameter 2", "Given Parameter 3"});
    }

    private static void logMessage(Logger logger, ResourceBundle rb, String msgKey, Object[] params) {
        logger.logrb(
                Level.SEVERE, // log level
                Log_i18n_MessagesFromResourceBoundle.class.getName(), //sourceClass - Name of the class that issued the logging request
                "main", //ourceMethod - Name of the method that issued the logging request
                rb, //bundle - Resource bundle to localize msg, can be null
                msgKey, //msg - The string message (or a key in the message catalog)
                params //params - Parameters to the message (optional, may be none).
        );
    }

}

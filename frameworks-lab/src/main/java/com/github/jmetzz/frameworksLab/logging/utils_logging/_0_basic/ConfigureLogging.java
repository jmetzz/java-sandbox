package com.github.jmetzz.frameworksLab.logging.utils_logging._0_basic;


import java.util.logging.Logger;

public class ConfigureLogging {

    static Logger lgr0 = Logger.getLogger("com"),
            lgr1 = Logger.getLogger("com.github"),
            lgr2 = Logger.getLogger("com.github.jmetzz"),
            api = Logger.getLogger("com.github.jmetzz.api"),
            core = Logger.getLogger("com.github.jmetzz.core"),
            rand = Logger.getLogger("random");

    public ConfigureLogging() {
        /*
		 * Set Additional formatters, Filters and Handlers for the loggers here.
		 * You cannot specify the Handlers for loggers except the root logger
		 * from the configuration file.
		 */
    }


    //{JVMArgs: -Djava.util.logging.config.file=log.prop}
    public static void main(String[] args) {
        sendLogMessages(lgr0);
        sendLogMessages(lgr1);
        sendLogMessages(lgr2);
        sendLogMessages(api);
        sendLogMessages(core);
        sendLogMessages(rand);
    }

    private static void sendLogMessages(Logger logger) {
        System.out.println(" Logger Name : " + logger.getName()
                + " Level: " + logger.getLevel());

        logger.finest("Finest");
        logger.finer("Finer");
        logger.fine("Fine");
        logger.config("Config");
        logger.info("Info");
        logger.warning("Warning");
        logger.severe("Severe");

    }
}

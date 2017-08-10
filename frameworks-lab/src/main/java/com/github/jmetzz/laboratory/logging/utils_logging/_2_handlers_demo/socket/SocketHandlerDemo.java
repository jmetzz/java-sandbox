package com.github.jmetzz.laboratory.logging.utils_logging._2_handlers_demo.socket;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

public class SocketHandlerDemo {

    private static Logger logger = Logger.getLogger(SocketHandlerDemo.class.getName());

    public static final String BASE_PROJECT_URL = "./frameworks-lab/";
    public static final String PACKAGE_RESOURCES = BASE_PROJECT_URL + "src/test/resources/logging-resources/";

   /*
    Simple network logging Handler.
    LogRecords are published to a network stream connection.
    By default the XMLFormatter class is used for formatting.
    The output IO stream is buffered, but is flushed after each LogRecord is written.
    */
   private SocketHandler handler = null;


    public SocketHandlerDemo(String host, int port) {
        try {
            /*
            The constructor public SocketHandler(String host, int port) then allows you
            to define where to send network log messages, or you can place the necessary
            settings in the logging.properties file and use the no-argument version of the constructor.
            */
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
        SocketHandlerDemo demo = new SocketHandlerDemo("localhost", 9999);
        /**
        This sends messages to port 9999 on localhost (the user's computer).
        Of course, by default, nothing is listening on the other end. You must create and run a server.
        @see #{LoggingServer}
        */
        demo.logMessage();
    }
}
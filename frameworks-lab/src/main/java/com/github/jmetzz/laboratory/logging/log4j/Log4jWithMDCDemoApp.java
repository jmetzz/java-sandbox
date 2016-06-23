package com.github.jmetzz.laboratory.logging.log4j;

import org.apache.log4j.*;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * MDC stands for Mapped Diagnostic Context.
 * It helps you to distinguish inter-leaving logs from multiple sources,
 * such as multiple user-requests coming in for a given servlet.
 * In this scenario each request of an user is serviced using a distinct thread,
 * which leaves multiple users logging to the same log file and the log
 * statements get inter-mixed.
 * So, if you use log4j for logging, then each thread can have it’s own MDC
 * which is  global to the entire thread.
 * Thus, MDC helps to distinguish those log messages in a non-invasive way
 * by allowing us to put the user-id (and other information) in a context-map
 * which is attached to the thread (of each user request) by the logger.
 * MDC is thread-safe and uses a Map internally to store the context information.
 */
public class Log4jWithMDCDemoApp {

    /*
    How to use MDC?
    a. Configure the information, which needs to be logged (user-id in this case) in the log4j.xml as part of ConversionPattern.
    b. In your respective class, before you start processing the user request, place the actual user-id in the context(MDC).
    c. Remove the context information from MDC at the end of the processing.
   */

    public static void main(String[] args) throws InterruptedException {

        /*
        Let's first make sure we run each session in it own thread by
        creating a ExecutorService that is going to run the threads
        */

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new MySession("Jean", UUID.randomUUID().toString()));
        executorService.execute(new MySession("Newton", UUID.randomUUID().toString()));

        // Make sure executor stops
        executorService.shutdown();

        // Blocks until all tasks have completed execution after a shutdown request
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

    }


    static class MySession implements Runnable {

        Logger logger = Logger.getLogger(MySession.class.getName());

        private final String userName;
        private final String sessionId;

        MySession(String userName, String sessionId) {
            this.userName = userName;
            this.sessionId = sessionId;
            configureLogger();
        }

        private void configureLogger() {

            /*
              Configure the information, which needs to be logged (user-name and session-id in this case)
              in the log4j.xml as part of ConversionPattern.
              Obviously you can also configure this in the log4j.xml as part of ConversionPattern.
             */
            PatternLayout layout = new PatternLayout();
            layout.setConversionPattern("%X{user-name} %X{session-id} %-7p %d [%t] %c %x - %m%n");

            // creates console appender
            ConsoleAppender consoleAppender = new ConsoleAppender();
            consoleAppender.setLayout(layout);
            consoleAppender.activateOptions();

            logger.addAppender(consoleAppender);


            /*
            before you start processing the user request, place the actual user-name and session-id in the context(MDC)
             */
            MDC.put("user-name", userName);
            MDC.put("session-id", sessionId);

            /*
            OBS: in case of Servlets, one can setup this info using a Filter, such as

            @Override
	        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                try {
			        MDC.put("user-name", "John Doe");
                    chain.doFilter(request, response);
                } finally {
                    MDC.remove("userName");
                }
            }
           */
        }


        @Override
        public void run() {
            logger.log(Level.ERROR, "Log some message ...");
            try {
                //simulate some processing time....
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {

            }

            /*
             Remove the context information from MDC at the end of the processing.
              */
            MDC.getContext().clear();
        }
    }

}

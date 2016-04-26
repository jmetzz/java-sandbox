package com.github.jmetzz.concurrency.standalone.producerAndConsumer;

/**
 * Created by Jean Metz on 18-Nov-15.
 */
public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }
}

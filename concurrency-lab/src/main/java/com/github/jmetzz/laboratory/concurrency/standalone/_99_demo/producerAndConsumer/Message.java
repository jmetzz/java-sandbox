package com.github.jmetzz.laboratory.concurrency.standalone._99_demo.producerAndConsumer;

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

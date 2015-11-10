package com.github.jmetzz.concurrency;

import java.io.Serializable;

/**
 * Created by exi853 on 10/11/2015.
 */
public class Message implements IMessage, Serializable {
    private static final long serialVersionUID = 1L;

    private final String code;

    private final String message;

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

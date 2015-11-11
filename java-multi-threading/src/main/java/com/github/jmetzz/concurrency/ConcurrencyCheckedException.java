package com.github.jmetzz.concurrency;

import java.io.Serializable;

/**
 * Created by exi853 on 10/11/2015.
 */
public class ConcurrencyCheckedException extends Exception implements Serializable {
    public static final long serialVersionUID = 1L;

    public ConcurrencyCheckedException(String message) {
        super(message);
    }
}

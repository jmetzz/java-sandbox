package com.github.jmetzz.concurrency.deployable.dataRetriver;

import java.io.Serializable;

public class ConcurrencyCheckedException extends Exception implements Serializable {
    public static final long serialVersionUID = 1L;

    public ConcurrencyCheckedException(String message) {
        super(message);
    }
}

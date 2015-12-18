package com.github.jmetzz.concurrency.deployable.dataRetriver;

/**
 * Created by exi853 on 10/11/2015.
 */
public class TimeOutException extends ConcurrencyCheckedException {

    private String service;

    public TimeOutException(String serviceName, String message) {
        super(message);
        this.service = serviceName;
    }
}

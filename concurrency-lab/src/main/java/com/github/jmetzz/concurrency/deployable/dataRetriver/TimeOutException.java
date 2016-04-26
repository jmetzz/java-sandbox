package com.github.jmetzz.concurrency.deployable.dataRetriver;

public class TimeOutException extends ConcurrencyCheckedException {

    private String service;

    public TimeOutException(String serviceName, String message) {
        super(message);
        this.service = serviceName;
    }
}

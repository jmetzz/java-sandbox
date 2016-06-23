package com.github.jmetzz.laboratory.concurrency.containerized.dataRetriverDemo;

public class TimeOutException extends ConcurrencyCheckedException {

    private String service;

    public TimeOutException(String serviceName, String message) {
        super(message);
        this.service = serviceName;
    }
}

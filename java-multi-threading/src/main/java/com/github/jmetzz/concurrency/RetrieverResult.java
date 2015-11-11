package com.github.jmetzz.concurrency;

import java.util.List;

/**
 * Created by exi853 on 10/11/2015.
 */
public class RetrieverResult<R> {

    final private List<IMessage> messages;
    final private boolean isSuccess;
    final private String serviceName;
    private R data;

    public RetrieverResult(List<IMessage> messages, boolean isSuccess, String serviceName) {
        this.messages = messages;
        this.isSuccess = isSuccess;
        this.serviceName = serviceName;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public List<IMessage> getMessages() {
        return messages;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public String getServiceName() {
        return serviceName;
    }

}

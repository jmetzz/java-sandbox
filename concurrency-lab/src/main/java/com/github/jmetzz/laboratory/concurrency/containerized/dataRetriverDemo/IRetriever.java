package com.github.jmetzz.laboratory.concurrency.containerized.dataRetriverDemo;


public interface IRetriever<R> {

    /**
     * Do the work
     */
    RetrieverResult<R> fetchData() throws ConcurrencyCheckedException;

    /**
     * Return the retriever name
     */
    String getServiceName();

    /**
     * Clean context of the retriever (auth, mdc)
     */
    void cleanContext();

    /**
     * Initialize the current thread with context of the main thread
     */
    void setContext();
}

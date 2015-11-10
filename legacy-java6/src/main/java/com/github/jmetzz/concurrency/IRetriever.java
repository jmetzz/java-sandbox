package com.github.jmetzz.concurrency;

/**
 * Created by exi853 on 10/11/2015.
 */
public interface IRetriever<R> {

    /**
     * Do the work
     */
    public abstract RetrieverResult<R> fetchData() throws ConcurrencyCheckedException;

    /**
     * Return the retriever name
     */
    public abstract String getServiceName();

    /**
     * Clean context of the retriever (auth, mdc)
     */
    public void cleanContext();

    /**
     * Initialize the current thread with context of the main thread
     */
    public void setContext();
}

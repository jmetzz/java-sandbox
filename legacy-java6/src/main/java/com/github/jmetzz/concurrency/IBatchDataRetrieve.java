package com.github.jmetzz.concurrency;

import java.util.Map;

/**
 * Created by exi853 on 10/11/2015.
 */
public interface IBatchDataRetrieve<R> {

    public void retrieveData(Map<String, IRetriever<R>> retrieverMap) throws ConcurrencyCheckedException;

    public Map<String, R> getResults() throws ConcurrencyCheckedException;
}

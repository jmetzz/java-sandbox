package com.github.jmetzz.concurrency.deployable.dataRetriver;

import java.util.Map;

/**
 * Created by exi853 on 10/11/2015.
 */
public interface IBatchDataRetrieve<R> {

    void retrieveData(Map<String, IRetriever<R>> retrieverMap) throws ConcurrencyCheckedException;

    Map<String, R> getResults() throws ConcurrencyCheckedException;
}

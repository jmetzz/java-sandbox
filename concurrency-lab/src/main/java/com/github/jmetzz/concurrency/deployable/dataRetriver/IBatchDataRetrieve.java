package com.github.jmetzz.concurrency.deployable.dataRetriver;

import java.util.Map;

public interface IBatchDataRetrieve<R> {

    void retrieveData(Map<String, IRetriever<R>> retrieverMap) throws ConcurrencyCheckedException;

    Map<String, R> getResults() throws ConcurrencyCheckedException;
}

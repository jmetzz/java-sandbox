package com.github.jmetzz.laboratory.concurrency.containerized.dataRetriverDemo;

import java.util.Map;

public interface IBatchDataRetrieve<R> {

    void retrieveData(Map<String, IRetriever<R>> retrieverMap) throws ConcurrencyCheckedException;

    Map<String, R> getResults() throws ConcurrencyCheckedException;
}

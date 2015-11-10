package com.github.jmetzz.concurrency;

import java.util.concurrent.ExecutorService;

/**
 * Created by exi853 on 10/11/2015.
 */
public class ASynchronousBatchRetriever<R> extends AbstractDataRetrieve<R, RetrieverWorker<R>> {

    private ExecutorService executorService;
    private Long timeOut;

//    private WorkManagerTaskExecutor workManager;

    @Override
    protected RetrieverWorker<R> retrieve(IRetriever<R> query) throws ConcurrencyCheckedException {
        return null;
    }

    @Override
    protected RetrieverResult<R> getRetrieverResult(String key) throws ConcurrencyCheckedException {
        return null;
    }
}

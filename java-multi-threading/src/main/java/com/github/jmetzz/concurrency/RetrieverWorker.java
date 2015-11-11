package com.github.jmetzz.concurrency;


import org.apache.log4j.Logger;

import javax.resource.spi.work.Work;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Created by exi853 on 10/11/2015.
 */
public class RetrieverWorker<R> implements Work {
    /*
    * FIXME - use interceptors and producers for logging
    * */
    private static final Logger LOGGER = Logger.getLogger(RetrieverWorker.class.getName());
    private static final long DEFAULT_TIME_OUT = 30000;

    private final Long timeout;
    private final IRetriever<R> retriever;
    private final ExecutorService executor;
    // FIXME -- add WorkManagerTaskExecutor
    // private final WorkManagerTaskExecutor workManager;
    private final List<RetrieverResult<R>> result = new ArrayList<RetrieverResult<R>>(1);

    private FutureTask<List<RetrieverResult<R>>> task;

    public RetrieverWorker(ExecutorService executor,
//                           WorkManagerTaskExecutor workManager,
                           IRetriever<R> retriever,
                           Long timeout) {
        this.retriever = retriever;
        this.executor = executor;
//        this.workManager = workManager;
        this.timeout = timeout;
    }


    public void execute() {
        task = new FutureTask<List<RetrieverResult<R>>>(this, result);
//        if (workManager != null) {
//            workManager.execute(task);
//        } else {
//            executor.execute(task);
//        }

        executor.execute(task);
    }

    public RetrieverResult<R> getData() {
        try {
            long time = DEFAULT_TIME_OUT;
            if (timeout != null && timeout > 0) {
                time = timeout;
            }
            return task.get(time, TimeUnit.MILLISECONDS).get(0);
        } catch (TimeoutException e) {
            LOGGER.error(e);
            task.cancel(true);
            LOGGER.error("task canceled for " + retriever.getServiceName());
            return createDefaultResult(new TimeOutException(retriever.getServiceName(), retriever.getServiceName()));
        } catch (Exception e) {
            LOGGER.error(e);
            return createDefaultResult(new ConcurrencyCheckedException(e.getMessage()));
        }
    }

    @Override
    public void run() {
        LOGGER.debug("Starting new task for service retriever "
                + retriever.getServiceName());

        try {
            RetrieverResult<R> data = retriever.fetchData();
            result.add(data);

        } catch (ConcurrencyCheckedException e) {
            LOGGER.error(e);
            result.add(createDefaultResult(e));
        }

    }

    @Override
    public void release() {
    }

    private RetrieverResult<R> createDefaultResult(ConcurrencyCheckedException e) {
        final List<IMessage> messages = new ArrayList<IMessage>(1);

        messages.add(new Message("ERR", e.getMessage()));

        return new RetrieverResult<R>(messages, false, retriever.getServiceName());
    }
}

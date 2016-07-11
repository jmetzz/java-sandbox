package com.github.jmetzz.laboratory.concurrency.standalone._0_fundametals.toyExample;

import com.github.jmetzz.laboratory.concurrency.standalone.synchronizationExample.runnables.AsyncRunnable;
import org.junit.Test;

/**
 * Created by Jean Metz on 14-Nov-15.
 */
public class AsynchRunnableTest {


    @Test
    public void testRunnable() {

        for (int i = 0; i < 10; i++)
            (new Thread(new AsyncRunnable(Integer.toString(i)))).start();
    }
}
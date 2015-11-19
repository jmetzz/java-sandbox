package com.github.jmetzz.concurrency.toyExample;

import org.junit.Test;

/**
 * Created by Jean Metz on 14-Nov-15.
 */
public class AsynchRunnableTest {


    @Test
    public void testRunnable() {

        for (int i = 0; i < 10; i++)
            (new Thread(new AsynchRunnable(Integer.toString(i)))).start();
    }
}
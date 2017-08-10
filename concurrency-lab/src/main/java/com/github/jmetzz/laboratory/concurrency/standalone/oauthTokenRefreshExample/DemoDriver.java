package com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.runnable.UsingLock;

public class DemoDriver {

	private static final int NTHREDS = 3;

	public static void main(String[] args) throws InterruptedException {


		testUsingLock(3, 30);
		System.out.println("\n\nFinished all threads using Lock");

//		testUsingLock(3, 30);
//		System.out.println("\n\nFinished all threads using Lock");
	}

	private static void testUsingLock(int nthreads, int nproc) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(nthreads);

		for (int i = 0; i < nproc; i++) {
			Runnable worker = new UsingLock("Run-" + i);
			executor.execute(worker);
		}

		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
	}

}
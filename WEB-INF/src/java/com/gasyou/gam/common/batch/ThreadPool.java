package com.gasyou.gam.common.batch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);

	public static void submit(Runnable runnable) {
		EXECUTOR.submit(runnable);
	}
}

package com.markusjais.java.futures.examples;

import java.util.Random;
import java.util.concurrent.Callable;

public class DownloadWeatherDataCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		// fake long running task
		Random random = new Random();
		Thread.sleep(random.nextInt(1000));
		return "sun is shining";
	}
}
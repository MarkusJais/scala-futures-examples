package com.markusjais.java.futures.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class JavaFutureExample {

	private static final int NTHREDS = 5;

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		Callable<String> worker = new DownloadWeatherDataCallable();
		Future<String> future = executor.submit(worker);
		String weather = "";
		
		// Do other stuff for a while

		try {
			weather = future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Something went wrong: " + e.getMessage());
		}
		
		System.out.println("wheater: " + weather);
		executor.shutdown();
	}
}
